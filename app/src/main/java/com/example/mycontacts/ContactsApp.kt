package com.example.mycontacts

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycontacts.navigation.ContactsNavHost


@Composable
fun ContactsApp(navController: NavHostController = rememberNavController()) {
    ContactsNavHost(navController = navController, onDelete = {})
}

@Composable
fun ContactsTopAppBar(
    title: String,
    navigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    if (navigateBack) {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)

                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun PreviewUI() {
  ContactsTopAppBar(title = "Sample Title", navigateBack = true)
}