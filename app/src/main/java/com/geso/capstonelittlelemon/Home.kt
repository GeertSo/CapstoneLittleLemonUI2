package com.geso.capstonelittlelemon

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
    Log.d(TAG, "Home: started")

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = Color.Unspecified
                ),
                modifier = Modifier.height(100.dp),
                title = {
                    Image(modifier = Modifier
                        //.fillMaxWidth()
                        .padding(top = 40.dp)
                        .border(BorderStroke(1.dp, Color.Red))
                        .height(40.dp),
                        alignment = Alignment.Center,
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Little Lemon Logo",
                        contentScale = ContentScale.Inside,
                    )
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center
        )
        {
            HeroSection()
            MenuBreakdown()
            MenuSection()
        }
    }
}

@Composable
fun MenuSection() {
    //TODO("Not yet implemented")
}

@Composable
fun MenuBreakdown() {
    //TODO("Not yet implemented")
}

@Composable
fun HeroSection() {
    Column (modifier = Modifier
        .fillMaxWidth()
        .background(color = LittleLemonTheme.colors.primary1)
        .padding(horizontal = 20.dp)
    ) {
        Text(modifier = Modifier.padding(),
            style = LittleLemonTheme.typography.displayTitle,
            color = LittleLemonTheme.colors.primary2,
            text = "Little Lemon")
        Row (
            Modifier
                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Column (modifier = Modifier
                .weight(0.5F)
                .padding(end = 10.dp)){
                Text(style = LittleLemonTheme.typography.subTitle,
                    color = Color.White,
                    text = "Chicago")
                Text(modifier = Modifier
                    .padding(top = 20.dp)
                    .width(200.dp),
                    style = LittleLemonTheme.typography.paragraph,
                    color = Color.White,
                    text = "We are a family-owned Mediterranean " +
                        "restaurant, focused on traditional recipes served with a modern twist")
            }
            Image(modifier = Modifier
                .weight(0.4F)
                .heightIn(min = 100.dp, max = 180.dp)
                //.border(BorderStroke(1.dp, Color.Yellow))
                .clip(RoundedCornerShape(20)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero image"
            )
        }
        SearchField()
    }
}

@Composable
fun SearchField() {
    val text = remember { mutableStateOf("") }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        textStyle = LittleLemonTheme.typography.highlight,
        //label = { Text("Enter Search Phrase") },
        placeholder = { Text(text = "Enter Search Phrase", fontSize = 16.sp) },
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
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        shape = RoundedCornerShape(30)
    )
}


@Preview
@Composable
fun HomePreview() {
    LittleLemonTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}