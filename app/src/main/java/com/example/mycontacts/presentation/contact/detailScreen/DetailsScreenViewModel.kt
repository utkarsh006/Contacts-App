package com.example.mycontacts.presentation.contact.detailScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.presentation.contact.ContactUiState
import com.example.mycontacts.domain.repository.ContactsRepository
import com.example.mycontacts.presentation.contact.toContact
import com.example.mycontacts.presentation.contact.toContactUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
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