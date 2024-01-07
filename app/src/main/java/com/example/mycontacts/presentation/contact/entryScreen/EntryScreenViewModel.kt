package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.model.InvalidContactException
import com.example.mycontacts.domain.usecases.ContactUseCases
import com.example.mycontacts.presentation.contact.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ContactState())
    val state: State<ContactState> = _state

    fun saveContact() {
        viewModelScope.launch {
            try {
                contactUseCases.addContact(
                    Contact(
                        id = state.value.id,
                        firstName = state.value.firstName,
                        lastName = state.value.lastName,
                        address = state.value.address,
                        gender = state.value.gender,
                    )
                )
            } catch (e: InvalidContactException) {
                println("Can't save contact")
            }
        }
    }
}