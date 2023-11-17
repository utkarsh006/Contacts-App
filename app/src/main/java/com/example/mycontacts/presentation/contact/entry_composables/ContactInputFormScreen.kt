package com.example.mycontacts.presentation.contact.entry_composables

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.ContactUiState
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactInputForm(
    contactUiState: ContactUiState,
    modifier: Modifier = Modifier,
    onValueChange: (ContactUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth(),
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

        val genderList = arrayOf("Male", "Female", "Other")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = contactUiState.gender,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.gender)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier.fillMaxWidth(),
                enabled = enabled,
                textStyle = TextStyle(color = Color.Black),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                )
            )
            if (enabled) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    genderList.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                onValueChange(contactUiState.copy(gender = item))
                                expanded = false
                            }) {
                            Text(text = item)
                        }
                    }
                }
            }

        }


    }
}


@Composable
fun datePickerDialog(
    context: Context,
    onDateSelected: (Date) -> Unit,
): DatePickerDialog {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayofMonth: Int ->
            val calender = Calendar.getInstance()
            calendar.set(mYear, mMonth, mDayofMonth)
            onDateSelected.invoke(calender.time)
        }, year, month, day
    )
    return mDatePickerDialog
}

@Preview(showBackground = true)
@Composable
fun ContactInputPreview() {
    ContactInputForm(contactUiState = ContactUiState())
}