package com.geso.capstonelittlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.livedata.observeAsState
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
            val menu = getMenu()
//            runOnUiThread {
//                menuItemsLiveData.value = menu.menu
//            }
            Log.d(TAG, "onCreate: read menu from file, 3rd entry = ${menu.menu[2]}")

            lifecycleScope.launch {
                withContext(IO) {
                    // delete all entries from the database, if existing already
                    database.menuDao().deleteAllMenuItems()
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
                    }
                    val menuItemList: List<MenuItem> = database.menuDao().getAllMenuItems()
                    Log.d(TAG, "onCreate: menuItemList from DB: $menuItemList")
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                MyNavigation(navController)
/*
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val items = menuItemsLiveData.observeAsState(emptyList())
                    MenuItems(items.value)
                }

 */
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

@Composable
fun MenuItems(
    items: List<MenuItemNetwork> = emptyList(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyColumn {
            itemsIndexed(items) { _, item ->
                MenuItemDetails(item.title)
            }
        }
    }
}

@Composable
fun MenuItemDetails(menuItem: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = menuItem)
    }
}