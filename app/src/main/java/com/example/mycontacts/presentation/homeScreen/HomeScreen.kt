package com.example.mycontacts.presentation.homeScreen

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
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.presentation.homeScreen.composables.HomeBody


@Composable
fun HomeScreen(
    navigateToEntryScreen: () -> Unit,
    navigateToUpdateScreen: (Int) -> Unit,
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEntryScreen,
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
            onContactClick = navigateToUpdateScreen,
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
