package com.example.mycontacts.room

import android.content.Context

interface AppContainer {
    val contactsRepository: ContactsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val contactsRepository: ContactsRepository by lazy {
        OfflineContactsRepository(ContactsDatabase.getDatabase(context).contactDao())
    }
}
