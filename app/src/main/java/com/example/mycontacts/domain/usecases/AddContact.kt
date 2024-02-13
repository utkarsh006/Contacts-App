package com.example.mycontacts.domain.usecases

import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.model.InvalidContactException
import com.example.mycontacts.domain.repository.ContactsRepository

class AddContact(
    private val repository: ContactsRepository
) {
    @Throws(InvalidContactException::class)
    suspend operator fun invoke(contact: Contact) {
        if (contact.firstName.isBlank() || contact.lastName.isBlank()) {
            throw InvalidContactException("First name and last name cannot be empty")
        }

        val existingContact = repository.getContactByName(contact.firstName, contact.lastName)
        if (existingContact != null) {
            throw InvalidContactException("Contact with the same name already exists")
        }

        repository.insertContact(contact)
    }
}
