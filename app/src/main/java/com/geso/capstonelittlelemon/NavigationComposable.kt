package com.geso.capstonelittlelemon

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavHostController, ctx: Context) {
    val profileEmpty: Boolean = firstNamePref.isEmpty() || lastNamePref.isEmpty() || eMailPref.isEmpty()

    Log.d(TAG, "MyNavigation: firstName = ${firstNamePref}, lastName = ${lastNamePref}, " +
            "email = ${eMailPref}, profileEmpty = ${profileEmpty}")

    NavHost(navController = navController,
        startDestination = if (profileEmpty) Onboarding.route else Home.route)
    {
        composable(Onboarding.route) {
            Onboarding(navController = navController, ctx = ctx)
        }
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}
