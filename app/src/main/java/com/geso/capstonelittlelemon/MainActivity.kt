package com.geso.capstonelittlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme


const val TAG = "LittleLemon"

const val PROFILESHAREDPREFERENCES = "ProfileSharedPref"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // read shared Preferences with Profile information - if not available, empty string is used
        val ctx = this.baseContext
        val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)

        val firstName = profileSharedPref.getString("firstName", "").toString()
        val lastName = profileSharedPref.getString("lastName", "").toString()
        val eMail = profileSharedPref.getString("eMail", "").toString()

        Log.d(TAG, "onCreate: firstName = $firstName, lastName = $lastName, email = $eMail")

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                MyNavigation(navController)
                Log.d(TAG, "in setContent in onCreate: firstName = $firstName, lastName = $lastName, email = $eMail")
            }
        }
    }
}
