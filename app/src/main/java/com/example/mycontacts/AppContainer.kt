package com.example.mycontacts

import android.content.Context
import com.example.mycontacts.data.dataSource.ContactsDatabase
import com.example.mycontacts.data.repository.OfflineContactsRepository
import com.example.mycontacts.domain.repository.ContactsRepository

interface AppContainer {
    val contactsRepository: ContactsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val contactsRepository: ContactsRepository by lazy {
        OfflineContactsRepository(ContactsDatabase.getDatabase(context).contactDao())
    }
}
