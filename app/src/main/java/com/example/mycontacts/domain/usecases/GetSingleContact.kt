package com.example.mycontacts.domain.usecases

import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.repository.ContactsRepository

class GetSingleContact(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(id: Int): Contact? {
        return repository.getContactStream(id)
    }
}
