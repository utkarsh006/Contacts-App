package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mycontacts.domain.usecases.ContactUseCases
import com.example.mycontacts.presentation.contact.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases
): ViewModel() {

    private val _state = mutableStateOf(ContactState())
    val state: State<ContactState> = _state

    fun saveContact(){

    }
}