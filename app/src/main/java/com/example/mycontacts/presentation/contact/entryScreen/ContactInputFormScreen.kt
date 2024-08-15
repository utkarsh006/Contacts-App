package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.ContactUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInputForm(
    contactUiState: ContactUiState,
    modifier: Modifier = Modifier,
    onValueChange: (ContactUiState) -> Unit = {},
    enabled: Boolean = true,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InputField(
            value = contactUiState.firstName,
            modifier = modifier,
            label = stringResource(R.string.contact_name_input_first_name)
        ) {
            onValueChange(contactUiState.copy(firstName = it))
        }

        InputField(
            value = contactUiState.lastName,
            modifier = modifier,
            label = stringResource(R.string.contact_name_input_last_name)
        ) {
            onValueChange(contactUiState.copy(lastName = it))
        }

        InputField(
            value = contactUiState.address,
            modifier = modifier,
            label = stringResource(R.string.contact_name_input_address)
        ) {
            onValueChange(contactUiState.copy(address = it))
        }

        val genderList = arrayOf("Male", "Female", "Other")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = contactUiState.gender,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.gender)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .clip(RoundedCornerShape(16.dp)),
                enabled = enabled,
                textStyle = TextStyle(color = Color.Black),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            if (enabled) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    genderList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                onValueChange(contactUiState.copy(gender = item))
                                expanded = false
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InputField(
    value: String,
    modifier: Modifier = Modifier,
    label: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        textStyle = TextStyle(color = Color.Black),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ContactInputPreview() {
    ContactInputForm(contactUiState = ContactUiState())
}
