package com.example.mycontacts.contact.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.contact.ContactUiState
import com.example.mycontacts.room.ContactsRepository
import com.example.mycontacts.contact.detail_composables.DetailsScreenDestination
import com.example.mycontacts.contact.toContact
import com.example.mycontacts.contact.toContactUiState
import kotlinx.coroutines.flow.*

class DetailsScreenViewModel(
    state: SavedStateHandle,
    private val contactsRepository: ContactsRepository
): ViewModel() {
    private val contactId: Int = checkNotNull(state[DetailsScreenDestination.contactIdArg])

    val uiState: StateFlow<ContactUiState> = contactsRepository.getContactStream(contactId)
        .filterNotNull()
        .map { it.toContactUiState(actionEnable = true) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ContactUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun deleteContact(){
        contactsRepository.deleteContact(uiState.value.toContact())
    }
}