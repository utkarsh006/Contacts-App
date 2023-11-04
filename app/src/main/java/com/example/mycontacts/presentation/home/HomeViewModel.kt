package com.example.mycontacts.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val contactList: List<Contact> = listOf()
)

class HomeViewModel(
    contactsRepository: ContactsRepository
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(listOf<Contact>())
    val persons = searchText.combine(_persons) { text, persons ->
        if (text.isBlank()) {
            persons
        } else {
            persons.filter {
                it.doesMatchSearchQuery(text)
            }
        }

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _persons.value
    )


    //This fn will be called from UI if user types something
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

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