package com.example.mycontacts.presentation.contact.entry_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.AppViewModelProvider
import com.example.mycontacts.presentation.contact.viewmodels.EntryScreenViewModel
import com.example.mycontacts.navigation.NavigationDestination
import kotlinx.coroutines.launch

object EntryScreenDestination: NavigationDestination {
    override val route = "contact_entry"
    override val titleRes = R.string.add_contact
}

@Composable
fun EntryScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: EntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(EntryScreenDestination.titleRes),
                navigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        EntryBody(
            contactUiState = viewModel.contactUiState,
            onContactValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveContact()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colors.background)
        )
    }
}
