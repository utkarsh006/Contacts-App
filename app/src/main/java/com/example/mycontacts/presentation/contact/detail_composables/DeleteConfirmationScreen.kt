package com.example.mycontacts.presentation.contact.detail_composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.viewmodels.DetailsScreenViewModel
import com.example.mycontacts.AppViewModelProvider

@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState by viewModel.uiState.collectAsState()

    AlertDialog(
        onDismissRequest = {},
        title = { Text(stringResource(R.string.delete_this_contact)) },
        text = { Text("${uiState.firstName} ${uiState.lastName}  will be removed from your Contacts") },
        modifier = modifier.padding(16.dp),
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(stringResource(R.string.delete))
            }
        }
    )
}
