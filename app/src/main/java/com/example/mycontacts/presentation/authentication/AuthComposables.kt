package com.example.mycontacts.presentation.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorDisplay(authState: AuthState) {
    if (authState is AuthState.Error) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = authState.message,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun LoadingButtonContent(authState: AuthState, buttonText: String) {
    if (authState is AuthState.Loading) {
        CircularProgressIndicator(
            modifier = Modifier.height(16.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        Text(text = buttonText)
    }
}