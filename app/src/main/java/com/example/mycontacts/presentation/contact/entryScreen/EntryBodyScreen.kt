package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.domain.model.Gender
import com.example.mycontacts.presentation.contact.ContactUiState
import com.example.mycontacts.presentation.contact.detailScreen.ContactPicture

@Composable
fun EntryBody(
    contactUiState: ContactUiState,
    onContactValueChange: (ContactUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactPicture(modifier = Modifier)

        ContactInputForm(
            contactUiState = contactUiState,
            onValueChange = onContactValueChange
        )

        Button(
            onClick = onSaveClick,
            enabled = contactUiState.actionEnable,
            modifier = modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.teal_700)
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.save_contact))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryBodyPreview() {
    EntryBody(contactUiState = ContactUiState(
        id = 9695,
        firstName = "Wiley Ochoa",
        lastName = "Penelope Bartlett",
        address = "persecuti",
        gender = Gender.MALE.type,
        actionEnable = true
    ), onContactValueChange = {}, onSaveClick = {})
}
