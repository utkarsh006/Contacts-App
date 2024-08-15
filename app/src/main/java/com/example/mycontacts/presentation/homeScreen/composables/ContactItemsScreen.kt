package com.example.mycontacts.presentation.homeScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.model.Gender.FEMALE
import com.example.mycontacts.domain.model.Gender.MALE
import com.example.mycontacts.presentation.contact.detailScreen.ContactPicture

@Composable
fun ContactItem(
    contact: Contact,
    onContactClick: (Contact) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onContactClick(contact) }
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactPicture(modifier = Modifier.size(60.dp))

        Spacer(modifier = modifier.width(8.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${contact.firstName} ${contact.lastName}",
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    painter = painterResource(
                        id = when (contact.gender) {
                            MALE.type -> R.drawable.ic_male
                            FEMALE.type -> R.drawable.ic_female
                            else -> R.drawable.ic_other_gender
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(24.dp)
                )
            }

            Text(
                text = contact.address,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    ContactItem(
        contact = Contact(1, "Utkarsh", "Saxena", "GT Road", MALE.type),
        onContactClick = {}
    )
}
