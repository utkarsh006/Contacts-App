package com.example.mycontacts.contact.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.contact.ContactUiState
import com.example.mycontacts.contact.EditScreenDestination
import com.example.mycontacts.contact.isValid
import com.example.mycontacts.contact.toContact
import com.example.mycontacts.contact.toContactUiState
import com.example.mycontacts.room.ContactsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class EditScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val contactsRepository: ContactsRepository
): ViewModel() {

    var contactUiState by mutableStateOf(ContactUiState())
        private set

    private val contactId: Int = checkNotNull(savedStateHandle[EditScreenDestination.contactIdArg])

    init {
        viewModelScope.launch {
            contactUiState = contactsRepository.getContactStream(contactId)
                .filterNotNull()
                .first()
                .toContactUiState(actionEnable = true)
        }
    }

    fun updateUiState(newContactUiState: ContactUiState) {
        contactUiState = newContactUiState.copy(actionEnable = newContactUiState.isValid())
    }

    suspend fun updateContact() {
        if (contactUiState.isValid()) {
            contactsRepository.updateContact(contactUiState.toContact())
        }
    }
}