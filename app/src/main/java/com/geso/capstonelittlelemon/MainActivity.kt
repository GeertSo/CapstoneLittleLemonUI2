package com.geso.capstonelittlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val TAG = "LittleLemon"

const val PROFILESHAREDPREFERENCES = "ProfileSharedPref"

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
//    private val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()
     private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MenuDatabase::class.java,
            "menu.db"
        ).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // read shared Preferences with Profile information - if not available, empty string is used
        val ctx = this.baseContext
        val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)

        val firstName = profileSharedPref.getString("firstName", "").toString()
        val lastName = profileSharedPref.getString("lastName", "").toString()
        val eMail = profileSharedPref.getString("eMail", "").toString()

        Log.d(TAG, "onCreate: firstName = $firstName, lastName = $lastName, email = $eMail")

        lifecycleScope.launch {
            Log.d(TAG, "onCreate: Start read menu from network")
            val menu = getMenu()
            Log.d(TAG, "onCreate: Finished read menu from network")

            lifecycleScope.launch {
                withContext(IO) {
//                    val menuItemListBeforeDelete = database.menuDao().getAllMenuItems()
//                    Log.d(TAG, "onCreate: menuItemList from DB BEFORE Delete: ${menuItemListBeforeDelete}")
                    // delete all entries from the database, if existing already
                    database.menuDao().deleteAllMenuItems()
//                    val menuItemListAfterDelete = database.menuDao().getAllMenuItems()
//                    Log.d(TAG, "onCreate: menuItemList from DB AFTER Delete: ${menuItemListAfterDelete}")

                    for (menuNetworkItem in menu.menu) {
                        val menuItem = MenuItem(
                            menuNetworkItem.id,
                            menuNetworkItem.title,
                            menuNetworkItem.description,
                            menuNetworkItem.price,
                            menuNetworkItem.image,
                            menuNetworkItem.category
                        )
                        database.menuDao().saveMenuItem(menuItem)
//                        Log.d(TAG, "onCreate: menu item saved: $menuItem")
                    }
//                    val menuItemList = database.menuDao().getAllMenuItems()
//                    Log.d(TAG, "onCreate: FINAL menuItemList from DB: $menuItemList")
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                MyNavigation(navController)
            }
        }
    }

    private suspend fun getMenu(): MenuNetwork {
        val response: MenuNetwork =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
        return response
    }
}
