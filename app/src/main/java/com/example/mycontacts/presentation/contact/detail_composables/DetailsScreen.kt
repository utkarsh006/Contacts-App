package com.example.mycontacts.presentation.contact.detail_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.presentation.contact.viewmodels.DetailsScreenViewModel
import kotlinx.coroutines.launch


//object DetailsScreenDestination : NavigationDestination {
//    override val route = "contact_details"
//    override val titleRes = R.string.contact_details
//    const val contactIdArg = "contactId"
//    val routeWithArgs = "$route/{$contactIdArg}"
//}

@Composable
fun DetailsScreen(
    navigateToEditContact: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.contact_details),
                navigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditContact(uiState.value.id) },
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Contact",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
    ) { innerPadding ->
        DetailsBody(
            contactUiState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteContact()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colors.background)
        )
    }
}
