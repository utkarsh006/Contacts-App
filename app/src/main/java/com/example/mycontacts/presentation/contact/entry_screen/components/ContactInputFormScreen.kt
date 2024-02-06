package com.example.mycontacts.presentation.contact.entry_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.ContactState
import com.example.mycontacts.presentation.contact.entry_screen.EntryScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInputForm(
    state: ContactState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    viewModel: EntryScreenViewModel = hiltViewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(stringResource(R.string.contact_name_input_first_name)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(stringResource(R.string.contact_name_input_last_name)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text(stringResource(R.string.address)) },
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        val genderList = arrayOf("Male", "Female", "Other")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = gender,
                onValueChange = { gender = it },
                readOnly = true,
                label = { Text(stringResource(R.string.gender)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                enabled = enabled,
                textStyle = TextStyle(color = Color.Black),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                )
            )

            if (enabled) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    genderList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                gender = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactInputPreview() {
    ContactInputForm(state = ContactState())
}
