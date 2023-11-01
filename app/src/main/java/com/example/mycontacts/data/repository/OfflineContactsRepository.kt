package com.example.mycontacts.data.repository

import com.example.mycontacts.data.dataSource.ContactDao
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class OfflineContactsRepository(private val contactDao: ContactDao) : ContactsRepository {

    override fun getAllContactsStream(): Flow<List<Contact>> = contactDao.getAllContacts()

    override fun getContactStream(id: Int): Flow<Contact?> = contactDao.getContact(id)

    override suspend fun insertContact(contact: Contact) = contactDao.insert(contact)

    override suspend fun deleteContact(contact: Contact) = contactDao.delete(contact)

    override suspend fun updateContact(contact: Contact) = contactDao.update(contact)
}