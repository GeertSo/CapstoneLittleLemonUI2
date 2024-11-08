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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Onboarding(navController: NavHostController, ctx: Context) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {SnackbarHost(hostState = snackbarHostState, modifier = Modifier.fillMaxWidth())},
        content = { paddingValues ->
            Log.d(TAG, "Onboarding: paddingValues = ${paddingValues}")
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
                Button(
                    onClick = {onClickfun(firstName, lastName, email, scope, snackbarHostState, navController, ctx)},
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

fun onClickfun(firstName: String, lastName: String, eMail: String,
               scope: CoroutineScope,
               snackbarHostState: SnackbarHostState,
               navController: NavHostController,
               ctx: Context) {
    val profileEmpty: Boolean = firstName.isEmpty() || lastName.isEmpty() || eMail.isEmpty()

    Log.d(TAG, "onClickfun: firstName = ${firstName}, lastName = ${lastName}, " +
            "email = ${eMail}, profileEmpty = ${profileEmpty}")
    if (profileEmpty) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Registration unsuccessful. Please enter all data.",
                duration = SnackbarDuration.Long) }
    } else {
        firstNamePref = firstName
        lastNamePref = lastName
        eMailPref = eMail
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Registration successfull!",
                duration = SnackbarDuration.Long) }
        val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)
        val profileEdit = profileSharedPref.edit()

        profileEdit.putString("firstName", firstName)
        profileEdit.putString("lastName", lastName)
        profileEdit.putString("eMail", eMail)
        profileEdit.commit()
        navController.navigate("home")
        // TODO delete the stack!
    }
}

/*
@Preview
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding()
    }
}
 */