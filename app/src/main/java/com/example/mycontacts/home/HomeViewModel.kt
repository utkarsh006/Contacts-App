package com.example.mycontacts.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.room.Contact
import com.example.mycontacts.room.ContactsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val contactList: List<Contact> = listOf()
)

class HomeViewModel(
    contactsRepository: ContactsRepository
): ViewModel(){
    val homeUiState: StateFlow<HomeUiState> =
        contactsRepository.getAllContactsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}