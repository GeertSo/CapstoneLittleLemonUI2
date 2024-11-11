package com.geso.capstonelittlelemon

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme


@Composable
fun Home(navController: NavHostController) {
    Log.d(TAG, "Home: started")

    Column {

        Text(text = "Home Screen",
            modifier = Modifier.padding(vertical = 40.dp))

        TextFieldWithIcons()

        Button(
            onClick = {navController.navigate(route = "profile")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 40.dp),
            border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
            shape = RoundedCornerShape(30), // = 30% percent
            colors = ButtonColors(containerColor = LittleLemonTheme.colors.primary2,
                contentColor = Color.Black, disabledContentColor = Color.Black,
                disabledContainerColor = LittleLemonTheme.colors.secondary2)
        ) {
            Text(text = "Profile", style = LittleLemonTheme.typography.paragraph)
        }
    }
}
@Composable
fun TextFieldWithIcons() {
    val text = remember { mutableStateOf("") }

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Search") },
        placeholder = { Text("Type here...") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            if (text.value.isNotEmpty()) {
                IconButton(onClick = { text.value = "" }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
    )
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