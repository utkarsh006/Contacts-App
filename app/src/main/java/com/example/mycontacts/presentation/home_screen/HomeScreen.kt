package com.example.mycontacts.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.navigation.NavScreen
import com.example.mycontacts.presentation.home_screen.components.HomeBody


@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.my_contacts),
                navigateBack = false
            )
        },
        // if no contact exists, go to entryScreen to fill all the details
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavScreen.EntryScreen.route) },
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Entry Contact",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            contactList = state.contactList,
            //else click on existing contact and go to the editScreen
            onContactClick = { navController.navigate(NavScreen.EditScreen.route) },
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
