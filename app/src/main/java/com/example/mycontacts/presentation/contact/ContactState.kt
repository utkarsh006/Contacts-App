package com.example.mycontacts.presentation.contact

import com.example.mycontacts.domain.model.Contact

data class ContactState(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val gender: String = "",
    val contactList: List<Contact> = listOf(),
)