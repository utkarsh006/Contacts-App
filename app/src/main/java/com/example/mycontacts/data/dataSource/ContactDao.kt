package com.example.mycontacts.data.dataSource

import androidx.room.*
import com.example.mycontacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * from contacts WHERE id = :id")
    fun getContactById(id: Int): Contact?

    @Query("SELECT * from contacts ORDER BY firstName ASC")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE firstName = :firstName AND lastName = :lastName LIMIT 1")
    fun getContactByName(firstName: String, lastName: String): Contact?
}
