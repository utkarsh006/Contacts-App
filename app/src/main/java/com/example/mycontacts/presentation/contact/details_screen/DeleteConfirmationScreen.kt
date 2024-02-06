package com.example.mycontacts.presentation.contact.details_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.R

@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    AlertDialog(
        onDismissRequest = {},
        title = { Text(stringResource(R.string.delete_this_contact)) },
        text = { Text("${state.firstName} ${state.lastName} will be removed from your Contacts") },
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
