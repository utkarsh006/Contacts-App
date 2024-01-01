package com.example.mycontacts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycontacts.navigation.ContactsNavHost


@Composable
fun ContactsApp(navController: NavHostController = rememberNavController()) {
    ContactsNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsTopAppBar(
    title: String,
    navigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
) {
    Scaffold(
        topBar = {
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
    ){
        _ ->
    }
}

@Preview
@Composable
fun PreviewUI() {
  ContactsTopAppBar(title = "Sample Title", navigateBack = true)
}