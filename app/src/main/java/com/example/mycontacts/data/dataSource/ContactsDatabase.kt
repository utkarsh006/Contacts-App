package com.example.mycontacts.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mycontacts.data.converters.DateConverter
import com.example.mycontacts.domain.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        const val DB_NAME = "contacts_db"
    }
}
