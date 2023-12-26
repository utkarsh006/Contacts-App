package com.example.mycontacts.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(route = "home_screen")
    object EntryScreen : NavScreen(route = "entry_screen")
    object DetailsScreen : NavScreen(route = "details_screen/{id}")
    object EditScreen : NavScreen(route = "edit_screen")
}
