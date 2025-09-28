package com.mohammadassad.mealm8.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.mohammadassad.mealm8.core.navigation.navigateToCategoryDetail

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MainScreen(
    navController: NavController,
    initialTabIndex: Int = 0
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            title = "Explore",
            icon = Icons.Default.Explore
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

    var selectedTabIndex by remember { mutableIntStateOf(initialTabIndex) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
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
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            selectedIndicatorColor = Color.Transparent,
                            disabledIconColor = Color.Gray,
                            disabledTextColor = Color.Gray
                        ),
                        onClick = {
                            selectedTabIndex = index
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTabIndex) {
                0 -> com.mohammadassad.mealm8.features.home.presentation.HomeScreen()
                1 -> com.mohammadassad.mealm8.features.explore.presentation.ExploreScreen(
                    onItemClick = { type, name ->
                        navController.navigateToCategoryDetail(type, name, selectedTabIndex)
                    }
                )
                2 -> com.mohammadassad.mealm8.features.search.presentation.SearchScreen()
                3 -> com.mohammadassad.mealm8.features.favourites.presentation.FavouritesScreen()
                else -> com.mohammadassad.mealm8.features.home.presentation.HomeScreen()
            }
        }
    }
}
