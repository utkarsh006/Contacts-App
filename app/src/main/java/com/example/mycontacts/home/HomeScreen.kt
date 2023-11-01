package com.example.mycontacts.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.ui.theme.AppViewModelProvider
import com.example.mycontacts.home.composables.HomeBody
import com.example.mycontacts.navigation.NavigationDestination

object HomeScreenDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.my_contacts
}

@Composable
fun HomeScreen(
    navigateToEntryScreen: () -> Unit,
    navigateToUpdateScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(HomeScreenDestination.titleRes),
                navigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEntryScreen,
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Entry Contact",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            contactList = homeUiState.contactList,
            onContactClick = navigateToUpdateScreen,
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colors.background)
        )
    }
}
