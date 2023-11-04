package com.example.mycontacts.presentation.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycontacts.R
import com.example.mycontacts.domain.model.Contact

@Composable
fun HomeBody(
    contactList: List<Contact>,
    onContactClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (contactList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.list_empty),
                contentDescription = null,
                modifier = modifier.size(200.dp)
            )
            Text(
                text = stringResource(R.string.no_contacts),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }

    } else {
        ContactList(
            contactList = contactList,
            onContactClick = { onContactClick(it.id) },

            )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    HomeBody(
        contactList = listOf(),
        onContactClick = {},
    )
}