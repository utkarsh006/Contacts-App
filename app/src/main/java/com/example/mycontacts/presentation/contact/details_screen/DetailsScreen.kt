package com.example.mycontacts.presentation.contact.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    navigateToEditContact: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.contact_details),
                navigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditContact(state.id) },
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Contact",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    ) { innerPadding ->
        DetailsBody(
            state = state,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteContact()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
