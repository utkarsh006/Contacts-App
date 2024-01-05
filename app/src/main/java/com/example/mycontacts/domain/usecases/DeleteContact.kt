package com.example.mycontacts.domain.usecases

import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.repository.ContactsRepository

class DeleteContact(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(contact: Contact) {
        repository.deleteContact(contact)
    }
}
