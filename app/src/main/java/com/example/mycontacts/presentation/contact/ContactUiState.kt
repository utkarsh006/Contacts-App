package com.example.mycontacts.presentation.contact

import com.example.mycontacts.domain.model.Contact

data class ContactUiState(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val gender: String = "",
    val actionEnable: Boolean = false
)

fun ContactUiState.toContact(): Contact = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    address = address,
    gender = gender,
)

fun Contact.toContactUiState(actionEnable: Boolean = false): ContactUiState = ContactUiState(
    id = id,
    firstName = firstName,
    lastName = lastName,
    address = address,
    gender = gender,
    actionEnable = actionEnable
)

fun ContactUiState.isValid(): Boolean {
    return firstName.isNotBlank()
}