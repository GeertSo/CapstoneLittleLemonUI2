package com.geso.capstonelittlelemon

import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val ctx: Context = LocalContext.current

    Scaffold(
        snackbarHost = {SnackbarHost(hostState = snackbarHostState, modifier = Modifier.fillMaxWidth())},
        content = { paddingValues ->
            Log.d(TAG, "Onboarding: paddingValues = $paddingValues")
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
                    onClick = {onClickRegister(firstName, lastName, email, scope,
                        snackbarHostState, navController, ctx)},
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
    )
}

fun onClickRegister(firstName: String, lastName: String, eMail: String,
                    scope: CoroutineScope,
                    snackbarHostState: SnackbarHostState,
                    navController: NavHostController,
                    ctx: Context) {
    val profileEmpty: Boolean = firstName.isEmpty() || lastName.isEmpty()
            || eMail.isEmpty() || !eMail.contains('@')

    Log.d(
        TAG, "onClickfun: firstName = ${firstName}, lastName = ${lastName}, " +
                "email = ${eMail}, profileEmpty = $profileEmpty"
    )
    if (profileEmpty) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Registration unsuccessful. Please enter all data.",
                duration = SnackbarDuration.Long
            )
        }
    } else {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Registration successfull!",
                duration = SnackbarDuration.Long
            )
        }
        val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)
        val profileEdit = profileSharedPref.edit()

        profileEdit.putString("firstName", firstName)
        profileEdit.putString("lastName", lastName)
        profileEdit.putString("eMail", eMail)
        profileEdit.apply()
        navController.navigate(route = "home",
            navOptions = navOptions { popUpTo(route = "onboarding") { inclusive = true } }
        )
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        val navController = rememberNavController()
        Onboarding(navController)
    }
}
