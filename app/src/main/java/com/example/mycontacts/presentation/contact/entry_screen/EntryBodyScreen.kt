package com.example.mycontacts.presentation.contact.entry_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.ContactState

@Composable
fun EntryBody(
    state: ContactState,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EntryScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Box(modifier = modifier.align(alignment = Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    )
            )
        }

        ContactInputForm(
            state = state,
        )

        Button(
            onClick = {
                navController.navigate(viewModel.onEvent(EntryUIEvent.SaveButtonClicked))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_contact))
        }
    }
}