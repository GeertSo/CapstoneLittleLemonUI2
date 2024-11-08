package com.geso.capstonelittlelemon

import android.app.Activity.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme


const val TAG = "LittleLemon"

const val PROFILESHAREDPREFERENCES = "ProfileSharedPref"
var firstNamePref: String = ""
var lastNamePref: String = ""
var eMailPref: String = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // read shared Preferences with Profile information - if not available, empty string is used
        val ctx = this.baseContext
        val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)
        val profileEdit = profileSharedPref.edit()

        firstNamePref = profileSharedPref.getString("firstName", "").toString()
        lastNamePref = profileSharedPref.getString("lastName", "").toString()
        eMailPref = profileSharedPref.getString("eMail", "").toString()

        Log.d(TAG, "onCreate: firstName = ${firstNamePref}, lastName = ${lastNamePref}, email = ${eMailPref}")

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                MyNavigation(navController, ctx)
                Log.d(TAG, "in setContent in onCreate: firstName = ${firstNamePref}, lastName = ${lastNamePref}, email = ${eMailPref}")
            }
        }
    }
    /*
    fun setProfileSharedPref(firstName: String, lastName: String, eMail: String) {
        val profileSharedPref = getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)
        val profileEdit = profileSharedPref.edit()


    }

     */
}
