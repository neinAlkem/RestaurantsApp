/*
//package com.example.restaurantsapp
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.FavoriteBorder
//import androidx.compose.material.icons.filled.Place
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.restaurantsapp.ui.theme.RestaurantsAppTheme
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//@Composable
//fun RestaurantsScreen() {
//    val viewModel : RestaurantsViewModel = viewModel()
//    Scaffold(
//        modifier = Modifier.fillMaxSize()
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//        ) {
//            Header() // Add the Header composable here
//            Spacer(modifier = Modifier.height(16.dp)) // Space between header and list
//         LazyColumn(
//                contentPadding = PaddingValues(
//                    vertical = 8.dp,
//                    horizontal = 8.dp
//                )
//            ) {
//                items(viewModel.state.value) { restaurant -> RestaurantsItem(restaurant) { id -> viewModel.toggleFavorite(id) }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Header() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp) // Adjust padding as needed
//    ) {
//        Text(
//            text = "Baghas Rizaluddin", // Replace with actual name
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Text(
//            text = "225150207111065", // Replace with actual NIM
//            style = MaterialTheme.typography.bodyMedium,
//            color = Color.Black // Ensure the color is readable
//        )
//    }
//}
//
//
//@Composable
//fun RestaurantsItem(item : Restaurant) {
//    val favoriteState = remember {
//        mutableStateOf(false)
//    }
//    val icon = if (favoriteState.value)
//        Icons.Filled.Favorite
//    else
//        Icons.Filled.FavoriteBorder
//    Card(
//        modifier = Modifier.padding(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            RestaurantIcon(Icons.Filled.Place, Modifier.weight(0.15f))
//            RestaurantDetails(
//                item.title,
//                item.description,
//                Modifier.weight(0.85f))
//            RestaurantIcon(icon, Modifier.weight(0.15f))
//        }
//    }
//}
//
//@Composable
//private fun FavoriteIcon(icon: ImageVector,
//                         modifier: Modifier,
//                         onClick: () -> Unit) {
//
//    Image(
//        imageVector = icon,
//        contentDescription =  "Favorite restaurant icon",
//        modifier = Modifier
//            .padding(8.dp)
//            .clickable { onClick() }
//    )
//}
//
//@Composable
//private fun RestaurantIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}) {
//    Image(
//        imageVector = icon,
//        contentDescription = "Restaurant icon",
//        modifier = modifier
//            .padding(8.dp)
//            .clickable { onClick() }
//    )
//}
//
//@Composable
//private fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
//    Column(modifier = modifier) {
//        Text(
//            text = title,
//            style = MaterialTheme.typography.titleMedium
//        )
//        // Apply alpha directly to the Text color
//        Text(
//            text = description,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Applying alpha directly
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    RestaurantsAppTheme {
//        RestaurantsScreen()
//    }
//}
//
*/
package com.example.restaurantsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantsapp.ui.theme.RestaurantsAppTheme

@Composable
fun RestaurantsScreen() {
    val viewModel: RestaurantsViewModel = viewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header() // Add the Header composable here
            Spacer(modifier = Modifier.height(16.dp)) // Space between header and list
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            ) {
                items(viewModel.state.value) { restaurant ->
                    RestaurantsItem(restaurant = restaurant, onFavoriteClick = {
                        viewModel.toggleFavorite(restaurant.id)
                    })
                }
            }
        }
    }
}

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Adjust padding as needed
    ) {
        Text(
            text = "Baghas Rizaluddin", // Replace with actual name
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "225150207111065", // Replace with actual NIM
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black // Ensure the color is readable
        )
    }
}

@Composable
fun RestaurantsItem(
    restaurant: Restaurant,
    onFavoriteClick: () -> Unit
) {
    val icon = if (restaurant.isFavorite) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            RestaurantDetails(
                title = restaurant.title,
                description = restaurant.description,
                Modifier.weight(0.7f)
            )
            RestaurantIcon(icon, Modifier.weight(0.15f), onClick = onFavoriteClick)
        }
    }
}

@Composable
private fun RestaurantIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() }
    )
}

@Composable
private fun RestaurantDetails(
    title: String,
    description: String,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        // Apply alpha directly to the Text color
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Applying alpha directly
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RestaurantsAppTheme {
        RestaurantsScreen()
    }
}
