package com.example.mycontacts.navigation.routes

import com.example.mycontacts.navigation.NavigationDestination

object LoginPageDestination : NavigationDestination {
    override val route = "login"
}

object SignupPageDestination : NavigationDestination {
    override val route = "signup"
}

object HomeScreenDestination : NavigationDestination {
    override val route = "home"
}

object ProfileScreenDestination : NavigationDestination {
    override val route = "profile"
}

object EntryScreenDestination : NavigationDestination {
    override val route = "contact_entry"
}

object EditScreenDestination : NavigationDestination {
    override val route = "contact_edit"
    const val contactIdArg = "contactId"
    val routeWithArgs = "$route/{$contactIdArg}"
}

object DetailsScreenDestination : NavigationDestination {
    override val route = "contact_details"
    const val contactIdArg = "contactId"
    val routeWithArgs = "$route/{$contactIdArg}"
}
