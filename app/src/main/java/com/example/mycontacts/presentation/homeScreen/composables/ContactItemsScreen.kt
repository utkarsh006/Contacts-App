package com.example.mycontacts.presentation.homeScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycontacts.R
import com.example.mycontacts.domain.model.Contact

@Composable
fun ContactItems(
    contact: Contact,
    onContactClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onContactClick(contact) }
        ) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = modifier.width(8.dp))

            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "${contact.firstName} ${contact.lastName}",
                    textAlign = TextAlign.Left,
                    modifier = modifier.align(Alignment.Start)
                )

                Spacer(modifier = modifier.height(5.dp))

                Text(
                    text = contact.gender,
                    textAlign = TextAlign.Left,
                    modifier = modifier.align(Alignment.Start)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    ContactItems(
        contact = Contact(
            1,
            "Utkarsh",
            "Saxena",
            "GT Road",
            "Male"
        ),
        onContactClick = {}
    )
}
