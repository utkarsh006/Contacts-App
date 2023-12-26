package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import kotlinx.coroutines.launch


@Composable
fun EntryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: EntryScreenViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.add_contact),
                navController = navController
            )
        }
    ) { innerPadding ->
        EntryBody(
            contactUiState = viewModel.contactUiState,
            onContactValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveContact()
                    navController.popBackStack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colors.background)
        )
    }
}
