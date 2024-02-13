package com.example.mycontacts.domain.usecases

import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class GetAllContacts(private val repository: ContactsRepository) {

    suspend operator fun invoke(): Flow<List<Contact>> {
        return repository.getAllContactsStream()
    }
}
