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
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    /*
      Login functionality
    */
    fun login(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return

        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }


    /*
      Signup functionality
    */
    fun signup(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return

        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    /*
      SignOut functionality
    */
    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

}
