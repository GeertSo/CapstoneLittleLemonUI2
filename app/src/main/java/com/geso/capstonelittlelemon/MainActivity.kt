package com.geso.capstonelittlelemon

import android.os.Bundle
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

     private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MenuDatabase::class.java,
            "menu.db"
        ).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val menu = getMenu()

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
