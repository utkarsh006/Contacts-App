package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.ContactUiState

@Composable
fun EntryBody(
    contactUiState: ContactUiState,
    onContactValueChange: (ContactUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Box(modifier = modifier.align(alignment = Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    )
            )
//        }

        ContactInputForm(
            contactUiState = contactUiState,
            onValueChange = onContactValueChange
        )

        Button(
            onClick = onSaveClick,
            enabled = contactUiState.actionEnable,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_contact))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryBodyPreview() {
    EntryBody(contactUiState = ContactUiState(), onContactValueChange = {}, onSaveClick = {})
}
