package com.example.mycontacts.contact.entry_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.contact.ContactUiState

@Composable
fun ContactInputForm(
    contactUiState: ContactUiState,
    modifier: Modifier = Modifier,
    onValueChange: (ContactUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = contactUiState.firstName,
            onValueChange = { onValueChange(contactUiState.copy(firstName = it)) },
            label = { Text(stringResource(R.string.contact_name_input_first_name)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        TextField(
            value = contactUiState.lastName,
            onValueChange = { onValueChange(contactUiState.copy(lastName = it)) },
            label = { Text(stringResource(R.string.contact_name_input_last_name)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        TextField(
            value = contactUiState.address,
            onValueChange = { onValueChange(contactUiState.copy(address = it)) },
            label = { Text(stringResource(R.string.address)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        TextField(
            value = contactUiState.gender,
            onValueChange = { onValueChange(contactUiState.copy(gender = it)) },
            label = { Text(stringResource(R.string.gender)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactInputPreview() {
    ContactInputForm(contactUiState = ContactUiState())
}