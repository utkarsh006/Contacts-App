package com.example.mycontacts.presentation.contact

data class ContactState(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val gender: String = "",
    val actionEnable: Boolean = false
)