package com.example.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycontacts.presentation.contact.EditScreen
import com.example.mycontacts.presentation.contact.detail_composables.DetailsScreen
import com.example.mycontacts.presentation.contact.entry_composables.EntryScreen
import com.example.mycontacts.presentation.home.HomeScreen

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
            HomeScreen(
                navigateToEntryScreen = { navController.navigate(NavScreen.EntryScreen.route) },
                navigateToUpdateScreen = {
                    navController.navigate("${NavScreen.DetailsScreen.route}/$it")
                }
            )
        }

        composable(route = NavScreen.EntryScreen.route) {
            EntryScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
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
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
