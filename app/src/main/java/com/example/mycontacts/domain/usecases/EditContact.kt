package com.example.mycontacts.domain.usecases

import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.model.InvalidContactException
import com.example.mycontacts.domain.repository.ContactsRepository

class EditContact(private val repository: ContactsRepository) {

    @Throws(InvalidContactException::class)
    suspend operator fun invoke(updatedContact: Contact) {
        if (updatedContact.firstName.isBlank() || updatedContact.lastName.isBlank()) {
            throw InvalidContactException("First name and last name cannot be empty")
        }

        repository.updateContact(updatedContact)
    }
}
