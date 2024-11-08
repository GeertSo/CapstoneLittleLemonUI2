package com.geso.capstonelittlelemon

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme


@Composable
fun Home(navController: NavHostController) {
    Log.d(TAG, "Home: started")
    Text(text = "Home Screen ${firstNamePref} ${lastNamePref} ${eMailPref}")
}

/*
@Preview
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home(navController)
    }
}
 */