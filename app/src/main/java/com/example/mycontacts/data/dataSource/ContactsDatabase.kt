package com.example.mycontacts.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycontacts.domain.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        const val DB_NAME = "contacts_db"
    }
}
