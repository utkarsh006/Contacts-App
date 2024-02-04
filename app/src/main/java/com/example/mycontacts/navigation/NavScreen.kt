package com.example.mycontacts.navigation

const val HomeScreenPath = "home_screen"
const val DetailsScreenPath = "details_screen"
const val EntryScreenPath = "entry_screen"
const val EditScreenPath = "edit_screen"

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(HomeScreenPath)
    object DetailsScreen : NavScreen(DetailsScreenPath)
    object EntryScreen : NavScreen(EntryScreenPath)
    object EditScreen : NavScreen(EditScreenPath)

}