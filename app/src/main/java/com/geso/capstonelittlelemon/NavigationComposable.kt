package com.geso.capstonelittlelemon

import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavHostController) {
    val ctx: Context = LocalContext.current
    val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)

    val firstName = profileSharedPref.getString("firstName", "").toString()
    val lastName = profileSharedPref.getString("lastName", "").toString()
    val eMail = profileSharedPref.getString("eMail", "").toString()

    val profileEmpty: Boolean = firstName.isEmpty() || lastName.isEmpty() || eMail.isEmpty()

    Log.d(TAG, "MyNavigation: firstName = ${firstName}, lastName = ${lastName}, " +
            "email = ${eMail}, profileEmpty = $profileEmpty")

    NavHost(navController = navController,
        startDestination = if (profileEmpty) Onboarding.route else Home.route)
    {
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}
