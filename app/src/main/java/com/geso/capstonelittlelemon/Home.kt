package com.geso.capstonelittlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.geso.capstonelittlelemon.ui.theme.LittleLemonTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
    Log.d(TAG, "Home: started")
    val ctx = LocalContext.current
    val database by lazy {
        Room.databaseBuilder(
            ctx,
            MenuDatabase::class.java,
            "menu.db"
        ).build()
    }
    val menuItems by database.menuDao().getAllMenuItems().observeAsState(emptyList())
    val categoriesSet: MutableSet<String> = mutableSetOf()
    menuItems.forEach { categoriesSet.add(it.category) }
//    categoriesSet.add("Drinks")     // for testing only!!!!
//    categoriesSet.add("Beverages")  // for testing only!!!!

    Scaffold(
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = " ",
                            Modifier
                                .weight(1f)
//                                .border(width = 1.dp, color = Color.Blue)
                        )
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(2f)
//                                .border(BorderStroke(1.dp, Color.Red))
                                .width(180.dp)
                            ,
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Little Lemon Logo",
                            contentScale = ContentScale.FillWidth
                        )
                        Row (
                            Modifier
                                .weight(1f)
//                                .border(1.dp, color = Color.Green)
                            ,
                            horizontalArrangement = Arrangement.End) {
                            IconButton(onClick = {
                                navController.navigate(route = "profile")
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Person Profile",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
        {
            var searchPhrase by remember { mutableStateOf("") }
            HeroSection(searchPhrase = searchPhrase, onSearchChange = {searchPhrase = it})
            val menuItemsSearch = menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }

            var categoriesSelected by remember { mutableStateOf(mutableMapOf<String, Boolean>())}
            // initialize selection value for keys which are not existing in categoriesSelected
            categoriesSet.forEach { if (!categoriesSelected.containsKey(it)) categoriesSelected[it] =
                false
            }

            MenuBreakdown(categoriesSet = categoriesSet,
                categoriesSelected = categoriesSelected,
                selectCategory =  {categoriesSelected =
                    categoriesSelected.mapValues { 
                        (catKey, selected) -> if (catKey == it) !selected else selected
                    } as MutableMap<String, Boolean>
                }
            )

//            Log.d(TAG, "Home: categoriesSelected = ${categoriesSelected}")

            if (categoriesSelected.containsValue(true)) {
                val menuItemsFilter =
                    menuItemsSearch.filter { categoriesSelected[it.category] == true }
                MenuSection(menuItems = menuItemsFilter)
            } else {
                MenuSection(menuItems = menuItemsSearch)
            }
        }
    }
}

@Composable
fun MenuSection(menuItems: List<MenuItem>) {
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)) {
                items(items = menuItems, itemContent = { item ->
//                    Log.d(TAG, "MenuSection: item = $item")
                    MenuItemComp(item)
                })
            }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemComp(item: MenuItem) {
    Column (modifier = Modifier.fillMaxWidth()) {
        HorizontalDivider(thickness = 1.dp,
            modifier = Modifier.padding(vertical = 10.dp))
        Text(text = item.title, style = LittleLemonTheme.typography.cardTitle)
        Row (modifier = Modifier
//            .border(1.dp, color = Color.Blue)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (modifier = Modifier.weight(0.7F)
//                .border(2.dp, color = Color.Red)
            ){
                Text(text = item.description,
                    modifier = Modifier.padding(vertical = 10.dp),
                    maxLines = 2,
                    style = LittleLemonTheme.typography.paragraph,
                    color = LittleLemonTheme.colors.primary1)
                Text(text = "$${item.price}",
                    style = LittleLemonTheme.typography.highlight,
                    color = LittleLemonTheme.colors.primary1)
            }
            GlideImage(
                model = item.image,
                contentDescription = "picture of ${item.title}",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(0.3F)
//                    .border(2.dp, color = Color.Red)
                    .fillMaxHeight(),
            )
        }
    }
}


@Composable
fun MenuBreakdown(categoriesSet: Set<String>, categoriesSelected: MutableMap<String, Boolean>, selectCategory: (String) -> Unit) {

//    Log.d(TAG, "MenuBreakdown: categoriesSet = $categoriesSet")
//    Log.d(TAG, "MenuBreakdown: categoriesSelected = $categoriesSelected")

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp, horizontal = 20.dp)) {
        Text(text = "ORDER FOR DELIVERY!",
            modifier = Modifier.padding(bottom = 10.dp),
            style = LittleLemonTheme.typography.sectionTitle)
        LazyRow (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)) {
            items(items = categoriesSet.toList(),
                itemContent = { item ->
                    Button(
                        onClick = {
                            selectCategory(item)
//                            Log.d(TAG, "MenuBreakdown: clicked Item: $item")
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        shape = RoundedCornerShape(50), // = 50% percent
                        colors = ButtonColors(
                            containerColor =
                            if (categoriesSelected.get(key = item) == true) LittleLemonTheme.colors.primary1 else LittleLemonTheme.colors.highlight1,
                            contentColor =
                                if (categoriesSelected.get(key = item) == true) LittleLemonTheme.colors.highlight1 else LittleLemonTheme.colors.primary1,
                            disabledContentColor = Color.Unspecified,
                            disabledContainerColor = Color.Unspecified
                        ),
                    ) {
                        Text(text = item, style = LittleLemonTheme.typography.sectionCategory)
                    }
            })
        }
    }
}

@Composable
fun HeroSection(searchPhrase: String, onSearchChange: (String) -> Unit) {
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
        TextField(
            value = searchPhrase,
            onValueChange = onSearchChange,
            textStyle = LittleLemonTheme.typography.highlight,
            //label = { Text("Enter Search Phrase") },
            placeholder = { Text(text = "Enter Search Phrase", fontSize = 16.sp) },
            singleLine = true,
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
//            trailingIcon = {
//                if (searchPhrase.isNotEmpty()) {
//                    IconButton(onClick = { searchPhrase = "" }) {
//                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
//                    }
//                }
//            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(30)
        )
    }
}


@Preview
@Composable
fun MenuBreakdownPreview() {
    LittleLemonTheme {
        val categoriesSet = setOf("Drinks", "Starter", "Main", "Dessert")
        var categoriesSelected = mutableMapOf<String,Boolean>()
        categoriesSet.forEach { if (!categoriesSelected.containsKey(it)) categoriesSelected[it] =
            false
        }
        MenuBreakdown(categoriesSet = categoriesSet, categoriesSelected = categoriesSelected,
            selectCategory =  {categoriesSelected =
                categoriesSelected.mapValues {
                        (catKey, selected) -> if (catKey == it) !selected else selected
                } as MutableMap<String, Boolean>
            })
    }

}

/*
@Preview
@Composable
fun HomePreview() {
    LittleLemonTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}

@Preview
@Composable
fun MenuSectionPreview() {
    var mItemList: List<MenuItem> =
        listOf(MenuItem(id=1, title="Greek Salad", description="The famous greek salad of crispy lettuce, peppers, olives, our Chicago.", price="10", image="https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true", category="starters"),
            MenuItem(id=2, title="Lemon Desert", description="Traditional homemade Italian Lemon Ricotta Cake.", price="10", image="https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true", category="desserts"),
            MenuItem(id=3, title="Grilled Fish", description="Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.", price="10", image="https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true", category="mains"),
            )
    MenuSection(menuItems = mItemList)
}

 */
