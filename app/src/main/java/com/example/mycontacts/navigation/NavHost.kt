package com.example.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycontacts.presentation.contact.detailScreen.DetailsScreen
import com.example.mycontacts.presentation.contact.editScreen.EditScreen
import com.example.mycontacts.presentation.contact.entryScreen.EntryScreen
import com.example.mycontacts.presentation.homeScreen.HomeScreen

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
            EntryScreen(navController = navController)
        }

        composable(
            route = NavScreen.DetailsScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }

        composable(
            route = NavScreen.EditScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            EditScreen(navController = navController)
        }

    }
}
