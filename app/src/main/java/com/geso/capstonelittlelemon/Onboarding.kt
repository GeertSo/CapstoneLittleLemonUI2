package com.geso.capstonelittlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column (Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween){
        Column {
            Image(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 30.dp)
                .height(40.dp),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo"
            )
            Text(text = "Let's get to know you",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LittleLemonTheme.colors.primary1)
                    .padding(vertical = 50.dp),
                textAlign = Center,
                color = Color.White,
                style = LittleLemonTheme.typography.sectionTitle
            )
        }
        Column (modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)){
            Text(text = "Personal Information",
                style = LittleLemonTheme.typography.sectionCategory
            )
            OutlinedTextField(value = firstName, onValueChange = {firstName = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                maxLines = 1,
                textStyle = LittleLemonTheme.typography.leadText,
                label = { Text("First Name") }
            )
            OutlinedTextField(value = lastName, onValueChange = {lastName = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                maxLines = 1,
                textStyle = LittleLemonTheme.typography.leadText,
                label = { Text("Last Name") }
            )
            OutlinedTextField(value = email, onValueChange = {email = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                maxLines = 1,
                textStyle = LittleLemonTheme.typography.leadText,
                label = { Text("Email") }
            )
        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 40.dp),
                    border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
            shape = RoundedCornerShape(30), // = 30% percent
            colors = ButtonColors(containerColor = LittleLemonTheme.colors.primary2,
                contentColor = Color.Black, disabledContentColor = Color.Black,
                disabledContainerColor = LittleLemonTheme.colors.secondary2)
        ) {
            Text(text = "Register", style = LittleLemonTheme.typography.paragraph)
        }
    }
}


@Preview
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding()
    }
}
