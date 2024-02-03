package com.example.mycontacts.presentation.contact.entryScreen

sealed class EntryUIEvent {
    data class FirstNameChanged(val firstName: String) : EntryUIEvent()
    data class LastNameChanged(val lastName: String) : EntryUIEvent()
    data class AddressChanged(val address: String) : EntryUIEvent()
    data class GenderChanged(val gender: String) : EntryUIEvent()

    object SaveButtonClicked : EntryUIEvent()
}