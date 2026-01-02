package com.example.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycontacts.presentation.authentication.LoginPage
import com.example.mycontacts.presentation.authentication.LoginPageDestination
import com.example.mycontacts.presentation.authentication.SignupPage
import com.example.mycontacts.presentation.authentication.SignupPageDestination
import com.example.mycontacts.presentation.contact.editScreen.EditScreen
import com.example.mycontacts.presentation.contact.editScreen.EditScreenDestination
import com.example.mycontacts.presentation.contact.detailScreen.DetailsScreen
import com.example.mycontacts.presentation.contact.detailScreen.DetailsScreenDestination
import com.example.mycontacts.presentation.contact.entryScreen.EntryScreen
import com.example.mycontacts.presentation.contact.entryScreen.EntryScreenDestination
import com.example.mycontacts.presentation.homeScreen.HomeScreen
import com.example.mycontacts.presentation.homeScreen.HomeScreenDestination

@Composable
fun ContactsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = LoginPageDestination.route,
        modifier = modifier
    ) {

        composable(route = LoginPageDestination.route) {
            LoginPage(
                navigateToSignup = {
                    navController.navigate(SignupPageDestination.route)
                },
                onLoginSuccess = {
                    navController.navigate(HomeScreenDestination.route) {
                        popUpTo(LoginPageDestination.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = SignupPageDestination.route) {
            SignupPage(
                navigateToLogin = {
                    navController.navigate(LoginPageDestination.route)
                },
                onSignupSuccess = {
                    navController.navigate(HomeScreenDestination.route) {
                        popUpTo(SignupPageDestination.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = HomeScreenDestination.route) {
            HomeScreen(
                navigateToEntryScreen = { navController.navigate(EntryScreenDestination.route) },
                navigateToUpdateScreen = {
                    navController.navigate("${DetailsScreenDestination.route}/$it")
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
            arguments = listOf(
                navArgument(DetailsScreenDestination.contactIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(
                navigateToEditContact = { navController.navigate("${EditScreenDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = EditScreenDestination.routeWithArgs,
            arguments = listOf(
                navArgument(EditScreenDestination.contactIdArg) {
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
