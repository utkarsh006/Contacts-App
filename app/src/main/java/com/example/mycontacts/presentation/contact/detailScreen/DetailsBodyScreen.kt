package com.example.mycontacts.presentation.contact.detailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.ContactUiState
import com.example.mycontacts.presentation.contact.entryScreen.ContactInputForm

@Composable
fun DetailsBody(
    contactUiState: ContactUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactPicture(modifier = Modifier)

        ContactInputForm(
            contactUiState = contactUiState,
            enabled = false,
        )

        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            modifier = modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(R.string.delete_button))
            }
        }

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false }
            )
        }
    }
}

@Composable
fun ContactPicture(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_profile),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsBodyPreview() {
    DetailsBody(contactUiState = ContactUiState(), onDelete = {})
}
