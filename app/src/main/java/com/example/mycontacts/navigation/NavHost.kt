package com.example.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycontacts.contact.EditScreen
import com.example.mycontacts.contact.EditScreenDestination
import com.example.mycontacts.contact.detail_composables.DetailsScreen
import com.example.mycontacts.contact.detail_composables.DetailsScreenDestination
import com.example.mycontacts.contact.entry_composables.EntryScreen
import com.example.mycontacts.contact.entry_composables.EntryScreenDestination
import com.example.mycontacts.home.HomeScreen
import com.example.mycontacts.home.HomeScreenDestination


@Composable
fun ContactsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeScreenDestination.route) {
            HomeScreen(
                navigateToEntryScreen = { navController.navigate(EntryScreenDestination.route) },
                navigateToUpdateScreen = {
                    navController.navigate("${DetailsScreenDestination.route}/${it}")
                }
            )
        }

        composable(route = EntryScreenDestination.route) {
            EntryScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = DetailsScreenDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsScreenDestination.contactIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(
                navigateToEditContact = { navController.navigate("${EditScreenDestination.route}/${it}") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = EditScreenDestination.routeWithArgs,
            arguments = listOf(navArgument(EditScreenDestination.contactIdArg) {
                type = NavType.IntType
            })
        ) {
            EditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}