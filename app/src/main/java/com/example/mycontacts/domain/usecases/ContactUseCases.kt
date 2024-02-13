package com.example.mycontacts.domain.usecases

data class ContactUseCases(
    val addContact: AddContact,
    val deleteContact: DeleteContact,
    val getAllContacts: GetAllContacts,
    val getSingleContact: GetSingleContact,
    val editContact: EditContact
)
