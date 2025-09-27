package com.mohammadassad.mealm8.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MainScreen(
    navController: NavController,
    onNavigateToSettings: () -> Unit
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            title = "Categories",
            icon = Icons.Default.Category
        ),
        BottomNavItem(
            title = "Search",
            icon = Icons.Default.Search
        ),
        BottomNavItem(
            title = "Favourites",
            icon = Icons.Default.Favorite
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) },
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedTabIndex) {
            0 -> HomeScreen()
            1 -> CategoriesScreen()
            2 -> SearchScreen()
            3 -> FavouritesScreen()
            else -> HomeScreen()
        }
    }
}
