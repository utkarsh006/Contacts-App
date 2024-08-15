package com.example.mycontacts.presentation.contact.entryScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mycontacts.presentation.contact.ContactUiState
import com.example.mycontacts.presentation.contact.isValid
import com.example.mycontacts.presentation.contact.toContact
import com.example.mycontacts.domain.repository.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository,
) : ViewModel() {
    var contactUiState by mutableStateOf(ContactUiState())
        private set

    fun updateUiState(newContactUiState: ContactUiState) {
        contactUiState = newContactUiState.copy(actionEnable = newContactUiState.isValid())
    }

    suspend fun saveContact() {
        if (contactUiState.isValid()) {
            contactsRepository.insertContact(contactUiState.toContact())
        }
    }
}
