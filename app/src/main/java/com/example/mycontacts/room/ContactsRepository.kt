package com.example.mycontacts.room

import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getAllContactsStream(): Flow<List<Contact>>

    fun getContactStream(id: Int): Flow<Contact?>

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    suspend fun updateContact(contact: Contact)
}