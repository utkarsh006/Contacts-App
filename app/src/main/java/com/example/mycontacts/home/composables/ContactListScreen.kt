package com.example.mycontacts.home.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mycontacts.room.Contact

@Composable
fun ContactList(
    contactList: List<Contact>,
    onContactClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(

    ) {
        items(items = contactList, key = { it.id }) { contact ->
            ContactItems(
                contact = contact,
                onContactClick = onContactClick
            )
            Divider()
        }
    }
}
