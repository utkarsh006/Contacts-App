package com.example.mycontacts.presentation.contact

import com.example.mycontacts.domain.model.Contact
import java.util.Date

data class ContactUiState(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val gender: String = "",
    val date: Date? = null,
    val actionEnable: Boolean = false
)

fun ContactUiState.toContact(): Contact = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    address = address,
    gender = gender,
    date = date
)

fun Contact.toContactUiState(actionEnable: Boolean = false): ContactUiState = ContactUiState(
    id = id,
    firstName = firstName,
    lastName = lastName,
    address = address,
    gender = gender,
    date = date,
    actionEnable = actionEnable
)

fun ContactUiState.isValid(): Boolean {
    return firstName.isNotBlank()
}