package com.example.mycontacts.presentation.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mycontacts.navigation.NavigationDestination


object SignupPageDestination : NavigationDestination {
    override val route = "signup"
}
@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    onSignupSuccess: () -> Unit = {}
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    // Navigate to home screen when authentication succeeds
    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            onSignupSuccess()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Signup Page", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email, onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            })

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password, onValueChange = {
                password = it
            },
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(text = "Password")
            }
        )

        // Show error message if any
        if (authState is AuthState.Error) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = (authState as AuthState.Error).message,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.signup(email, password)
            },
            enabled = authState !is AuthState.Loading
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.height(16.dp),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(text = "Create Account")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navigateToLogin()
        }) {
            Text(text = "Already have an account, Login")
        }
    }
}