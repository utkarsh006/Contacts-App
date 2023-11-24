package com.example.mycontacts.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen("home_screen")
    object EntryScreen : NavScreen("entry_screen")
    object DetailsScreen : NavScreen("details_screen")
    object EditScreen : NavScreen("edit_screen")
}