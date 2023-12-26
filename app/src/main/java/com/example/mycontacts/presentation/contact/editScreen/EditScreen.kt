package com.example.mycontacts.presentation.contact.editScreen

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
import com.example.mycontacts.presentation.contact.entryScreen.EntryBody
import kotlinx.coroutines.launch


@Composable
fun EditScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EditScreenViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.edit_contact),
                navController = navController
            )
        }
    ) { innerPadding ->
        EntryBody(
            contactUiState = viewModel.contactUiState,
            onContactValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateContact()
                    navController.popBackStack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colors.background)
        )
    }
}
