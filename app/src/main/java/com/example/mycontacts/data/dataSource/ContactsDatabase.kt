package com.example.mycontacts.data.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycontacts.domain.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var Instance: ContactsDatabase? = null

        fun getDatabase(context: Context): ContactsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ContactsDatabase::class.java, "contacts_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}