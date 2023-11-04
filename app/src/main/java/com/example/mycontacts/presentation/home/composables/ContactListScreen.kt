package com.example.mycontacts.presentation.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.presentation.home.HomeViewModel

@Composable
fun ContactList(
    contactList: List<Contact>,
    onContactClick: (Contact) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = viewModel<HomeViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val persons by viewModel.persons.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            placeholder = {
                Text(
                    text = "Search a Contact",
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                    tint = Red
                )
            },

            trailingIcon = {
                if (searchText.isNotEmpty())
                    IconButton(onClick = { viewModel.onSearchTextChange("") }) {
                        Icon(
                            imageVector = Icons.Outlined.Close, contentDescription = "", tint = Red
                        )
                    }
            },
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn() {
                items(items = persons) { person ->
                    Text(
                        text = "${person.firstName} ${person.lastName}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }
            }

//            LazyColumn() {
//                items(items = contactList, key = { it.id }) { contact ->
//                    ContactItems(
//                        contact = contact,
//                        onContactClick = onContactClick
//                    )
//                    Divider()
//                }
//            }
        }
    }

}


@Preview
@Composable
fun ContactListPreview() {
    ContactList(
        contactList = listOf(),
        onContactClick = {},
    )
}