package com.example.mycontacts.presentation.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    /*
      Check for auth status
    */
    fun checkAuthStatus() {
        _authState.value =
            if (auth.currentUser == null) AuthState.Unauthenticated
            else AuthState.Authenticated
    }

    /*
      Login functionality
    */
    fun login(email: String, password: String) {
        authenticate(email, password) {
            auth.signInWithEmailAndPassword(email, password)
        }
    }


    /*
      Signup functionality
    */
    fun signup(email: String, password: String) {
        authenticate(email, password) {
            auth.createUserWithEmailAndPassword(email, password)
        }
    }

    /*
       SignOut functionality
    */
    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    private fun authenticate(
        email: String,
        password: String,
        action: () -> com.google.android.gms.tasks.Task<*>
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }

        _authState.value = AuthState.Loading

        action().addOnCompleteListener { task ->
            _authState.value =
                if (task.isSuccessful) {
                    AuthState.Authenticated
                } else {
                    AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
        }
    }
}

