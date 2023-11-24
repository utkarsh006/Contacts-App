package com.example.mycontacts.presentation.contact.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.domain.repository.ContactsRepository
import com.example.mycontacts.navigation.NavScreen
import com.example.mycontacts.presentation.contact.ContactUiState
import com.example.mycontacts.presentation.contact.isValid
import com.example.mycontacts.presentation.contact.toContact
import com.example.mycontacts.presentation.contact.toContactUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contactsRepository: ContactsRepository
): ViewModel() {

    var contactUiState by mutableStateOf(ContactUiState())
        private set

    private val contactId: Int = checkNotNull(savedStateHandle[NavScreen.EditScreen.route])

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