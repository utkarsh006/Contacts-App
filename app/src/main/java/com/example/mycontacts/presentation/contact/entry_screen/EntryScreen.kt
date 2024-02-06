package com.example.mycontacts.presentation.contact.entry_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.entry_screen.components.EntryBody

@Composable
fun EntryScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: EntryScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.add_contact),
                navigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        EntryBody(
            state = state,
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
