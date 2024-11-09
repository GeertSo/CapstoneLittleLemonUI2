package com.geso.capstonelittlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    firstName = "John"
    lastName = "Doe"
    email = "example@domain.com"

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.Unspecified
                ),
                title = {
                    Image(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 30.dp)
                        .height(40.dp),
                        alignment = Alignment.Center,
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Little Lemon Logo"
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Unspecified,
                contentColor = Color.Unspecified,
            ) {
                Button(
                    onClick = {/* TODO */},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
                    shape = RoundedCornerShape(30), // = 30% percent
                    colors = ButtonColors(containerColor = LittleLemonTheme.colors.primary2,
                        contentColor = Color.Black, disabledContentColor = Color.Black,
                        disabledContainerColor = LittleLemonTheme.colors.secondary2)
                ) {
                    Text(text = "Logout", style = LittleLemonTheme.typography.paragraph)
                }

            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column (modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)){
                Text(text = "Personal Information",
                    style = LittleLemonTheme.typography.sectionCategory
                )
                OutlinedTextField(value = firstName, onValueChange = {firstName = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    singleLine = true,
                    textStyle = LittleLemonTheme.typography.leadText,
                    label = { Text("First Name") },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Person Icon") },
                    trailingIcon = {
                        if (firstName.isNotEmpty()) {
                            IconButton(onClick = { firstName = "" }) {
                                Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                            }
                        }
                    },
                )
                OutlinedTextField(value = lastName, onValueChange = {lastName = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    singleLine = true,
                    textStyle = LittleLemonTheme.typography.leadText,
                    label = { Text("Last Name") },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Person Icon") },
                    trailingIcon = {
                        if (lastName.isNotEmpty()) {
                            IconButton(onClick = { lastName = "" }) {
                                Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                            }
                        }
                    },
                )
                OutlinedTextField(value = email, onValueChange = {email = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    singleLine = true,
                    textStyle = LittleLemonTheme.typography.leadText,
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "email Icon") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        if (email.isNotEmpty()) {
                            IconButton(onClick = { email = "" }) {
                                Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                            }
                        }
                    },
                    isError = email.isNotEmpty() && !email.contains('@')
                )

            }
            Button(
                onClick = {/* TODO */},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
                shape = RoundedCornerShape(30), // = 30% percent
                colors = ButtonColors(containerColor = LittleLemonTheme.colors.primary2,
                    contentColor = Color.Black, disabledContentColor = Color.Black,
                    disabledContainerColor = LittleLemonTheme.colors.secondary2)
            ) {
                Text(text = "Update & Back To Home", style = LittleLemonTheme.typography.paragraph)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfilePreview() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    firstName = "John"
    lastName = "Doe"
    email = "example@domain.com"


    LittleLemonTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.Unspecified,
                        titleContentColor = Color.Unspecified
                    ),
                    title = {
                        Image(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, bottom = 30.dp)
                            .height(40.dp),
                            alignment = Alignment.Center,
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Little Lemon Logo"
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Unspecified,
                    contentColor = Color.Unspecified,
                ) {
                    Button(
                        onClick = {/* TODO */},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
                        shape = RoundedCornerShape(30), // = 30% percent
                        colors = ButtonColors(containerColor = LittleLemonTheme.colors.primary2,
                            contentColor = Color.Black, disabledContentColor = Color.Black,
                            disabledContainerColor = LittleLemonTheme.colors.secondary2)
                    ) {
                        Text(text = "Logout", style = LittleLemonTheme.typography.paragraph)
                    }

                }
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column (modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)){
                    Text(text = "Personal Information",
                        style = LittleLemonTheme.typography.sectionCategory
                    )
                    OutlinedTextField(value = firstName, onValueChange = {firstName = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        singleLine = true,
                        textStyle = LittleLemonTheme.typography.leadText,
                        label = { Text("First Name") },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Person Icon") },
                        trailingIcon = {
                            if (firstName.isNotEmpty()) {
                                IconButton(onClick = { firstName = "" }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                                }
                            }
                        },
                    )
                    OutlinedTextField(value = lastName, onValueChange = {lastName = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        singleLine = true,
                        textStyle = LittleLemonTheme.typography.leadText,
                        label = { Text("Last Name") },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Person Icon") },
                        trailingIcon = {
                            if (lastName.isNotEmpty()) {
                                IconButton(onClick = { lastName = "" }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                                }
                            }
                        },
                    )
                    OutlinedTextField(value = email, onValueChange = {email = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        singleLine = true,
                        textStyle = LittleLemonTheme.typography.leadText,
                        label = { Text("Email") },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "email Icon") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        trailingIcon = {
                            if (email.isNotEmpty()) {
                                IconButton(onClick = { email = "" }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                                }
                            }
                        },
                        isError = email.isNotEmpty() && !email.contains('@')
                    )

                }
                Button(
                    onClick = {/* TODO */},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
                    shape = RoundedCornerShape(30), // = 30% percent
                    colors = ButtonColors(containerColor = LittleLemonTheme.colors.primary2,
                        contentColor = Color.Black, disabledContentColor = Color.Black,
                        disabledContainerColor = LittleLemonTheme.colors.secondary2)
                ) {
                    Text(text = "Update & Back To Home", style = LittleLemonTheme.typography.paragraph)
                }
            }
        }
    }
}