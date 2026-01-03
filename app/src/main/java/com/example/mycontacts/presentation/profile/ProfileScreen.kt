package com.example.mycontacts.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.ContactsTopAppBar
import com.example.mycontacts.R
import com.example.mycontacts.navigation.NavigationDestination
import com.example.mycontacts.presentation.authentication.AuthViewModel

object ProfileScreenDestination : NavigationDestination {
    override val route = "profile"
}

@Composable
fun ProfileScreen(
    navigateBack: () -> Unit,
    onLogout: () -> Unit = {},
    authViewModel: AuthViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ContactsTopAppBar(
                title = stringResource(R.string.profile),
                navigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.logout),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clickable {
                        authViewModel.signOut()
                        onLogout()
                    },
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}