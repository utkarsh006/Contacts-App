package com.example.mycontacts.data.repository

import com.example.mycontacts.data.dataSource.ContactDao
import com.example.mycontacts.domain.model.Contact
import com.example.mycontacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class OfflineContactsRepository(private val contactDao: ContactDao) : ContactsRepository {

    override fun getAllContactsStream(): Flow<List<Contact>> {
        return contactDao.getAllContacts()
    }

    override fun getContactStream(id: Int): Contact? {
        return contactDao.getContactById(id)
    }

    override suspend fun insertContact(contact: Contact) {
        return contactDao.insert(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        return contactDao.delete(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        return contactDao.update(contact)
    }

    override suspend fun getContactByName(firstName: String, lastName: String): Contact? {
        return contactDao.getContactByName(firstName, lastName)
    }
}
