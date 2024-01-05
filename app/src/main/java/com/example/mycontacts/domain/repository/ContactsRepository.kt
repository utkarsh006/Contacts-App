package com.example.mycontacts.domain.repository

import com.example.mycontacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    fun getAllContactsStream(): Flow<List<Contact>>

    fun getContactStream(id: Int): Contact?

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    suspend fun updateContact(contact: Contact)

    suspend fun getContactByName(firstName: String, lastName: String): Contact?
}
