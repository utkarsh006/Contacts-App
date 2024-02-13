package com.example.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycontacts.presentation.contact.details_screen.DetailsScreen
import com.example.mycontacts.presentation.contact.edit_screen.EditScreen
import com.example.mycontacts.presentation.contact.entry_screen.EntryScreen
import com.example.mycontacts.presentation.home_screen.HomeScreen

@Composable
fun ContactsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = NavScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = NavScreen.EntryScreen.route) {
            EntryScreen(
                navController = navController,
                onNavigateUp = { navController.navigateUp() },
            )
        }

        composable(
            route = NavScreen.DetailsScreen.route,
            arguments = listOf(
                navArgument(NavScreen.DetailsScreen.route) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(
                navigateToEditContact = { navController.navigate("${NavScreen.EditScreen.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = NavScreen.EditScreen.route,
            arguments = listOf(
                navArgument(NavScreen.EditScreen.route) {
                    type = NavType.IntType
                }
            )
        ) {
            EditScreen(
                navController = navController,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
