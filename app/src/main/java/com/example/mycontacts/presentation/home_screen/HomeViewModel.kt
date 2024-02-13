package com.example.mycontacts.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.usecases.ContactUseCases
import com.example.mycontacts.presentation.contact.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ContactState())
    val state: State<ContactState> = _state

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    init{
        getAllContacts()
    }

    //Display all Contacts
    private fun getAllContacts() {
        viewModelScope.launch {
            try {
                val contacts = contactUseCases.getAllContacts.invoke().toList().flatten()
                _state.value = _state.value.copy(contactList = contacts)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // This fn will be called from UI if user types something
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun searchUser(contactList: List<Contact>): List<Contact> {
        val list = if (searchText.value.isEmpty()) {
            contactList
        } else {
            contactList.filter {
                it.doesMatchSearchQuery(searchText.value)
            }
        }
        return list
    }

}
