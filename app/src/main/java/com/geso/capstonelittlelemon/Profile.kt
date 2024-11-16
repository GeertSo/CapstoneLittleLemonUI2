package com.geso.capstonelittlelemon

import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var eMail by remember { mutableStateOf("") }

    val ctx = LocalContext.current
    val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)

    firstName = profileSharedPref.getString("firstName", "").toString()
    lastName = profileSharedPref.getString("lastName", "").toString()
    eMail = profileSharedPref.getString("eMail", "").toString()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState,
            modifier = Modifier.fillMaxWidth()) },
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.Unspecified
                ),
                title = {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(180.dp)
                            ,
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Little Lemon Logo",
                            contentScale = ContentScale.FillWidth,
                            alignment = Alignment.Center
                        )

                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Unspecified,
                contentColor = Color.Unspecified,
            ) {
                Button(
                    onClick = {onClickLogout(ctx, navController)},
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
            Column (modifier = Modifier.padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center)
            {
                Text(
                    text = "Personal Information",
                    style = LittleLemonTheme.typography.cardTitle
                )
                OutlinedTextField(
                    value = firstName, onValueChange = { firstName = it },
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
                OutlinedTextField(
                    value = lastName, onValueChange = { lastName = it },
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
                OutlinedTextField(
                    value = eMail, onValueChange = { eMail = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    singleLine = true,
                    textStyle = LittleLemonTheme.typography.leadText,
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "email Icon") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        if (eMail.isNotEmpty()) {
                            IconButton(onClick = { eMail = "" }) {
                                Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                            }
                        }
                    },
                    isError = eMail.isNotEmpty() && !eMail.contains('@')
                )


                Button(
                    onClick = {
                        onClickUpdate(
                            firstName,
                            lastName,
                            eMail,
                            scope,
                            snackbarHostState,
                            navController,
                            ctx
                        )
                    },
                    modifier = Modifier
                        .width(300.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 20.dp),
                    border = BorderStroke(1.dp, LittleLemonTheme.colors.secondary1),
                    shape = RoundedCornerShape(30), // = 30% percent
                    colors = ButtonColors(
                        containerColor = LittleLemonTheme.colors.primary2,
                        contentColor = Color.Black, disabledContentColor = Color.Black,
                        disabledContainerColor = LittleLemonTheme.colors.secondary2
                    )
                ) {
                    Text(
                        text = "Update & Back To Home",
                        style = LittleLemonTheme.typography.paragraph
                    )
                }
            }
    }
}

fun onClickUpdate(firstName: String, lastName: String, eMail: String,
                  scope: CoroutineScope,
                  snackbarHostState: SnackbarHostState,
                  navController: NavHostController,
                  ctx: Context
) {
    val profileEmpty: Boolean = firstName.isEmpty() || lastName.isEmpty()
            || eMail.isEmpty() || !eMail.contains('@')

    Log.d(TAG, "onClickfun: firstName = ${firstName}, lastName = ${lastName}, " +
            "email = ${eMail}, profileEmpty = $profileEmpty")
    if (profileEmpty) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Registration unsuccessful. Please enter all data.",
                duration = SnackbarDuration.Long) }
    } else {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Registration successfull!",
                duration = SnackbarDuration.Long) }
        val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)
        val profileEdit = profileSharedPref.edit()

        profileEdit.putString("firstName", firstName)
        profileEdit.putString("lastName", lastName)
        profileEdit.putString("eMail", eMail)
        profileEdit.apply()
        navController.navigate(route = "home")
    }
}

fun onClickLogout(ctx: Context, navController: NavHostController) {
    val profileSharedPref = ctx.getSharedPreferences(PROFILESHAREDPREFERENCES, MODE_PRIVATE)
    val profileEdit = profileSharedPref.edit()

    profileEdit.remove("firstName")
    profileEdit.remove("lastName")
    profileEdit.remove("eMail")
    profileEdit.apply()
    navController.navigate(route = "onboarding",
        navOptions = navOptions { popUpTo(route = "onboarding"){inclusive = true} }
    )

}




@Preview
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        val navController = rememberNavController()
        Profile(navController)
    }
}
