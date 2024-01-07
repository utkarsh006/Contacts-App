package com.example.mycontacts.di

import android.app.Application
import androidx.room.Room
import com.example.mycontacts.data.dataSource.ContactsDatabase
import com.example.mycontacts.data.repository.OfflineContactsRepository
import com.example.mycontacts.domain.repository.ContactsRepository
import com.example.mycontacts.domain.usecases.AddContact
import com.example.mycontacts.domain.usecases.ContactUseCases
import com.example.mycontacts.domain.usecases.DeleteContact
import com.example.mycontacts.domain.usecases.EditContact
import com.example.mycontacts.domain.usecases.GetAllContacts
import com.example.mycontacts.domain.usecases.GetSingleContact
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(app: Application): ContactsDatabase {
        return Room.databaseBuilder(
            app,
            ContactsDatabase::class.java,
            ContactsDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(db: ContactsDatabase): ContactsRepository {
        return OfflineContactsRepository(db.contactDao())
    }

    @Provides
    @Singleton
    fun provideContactUseCases(repository: ContactsRepository): ContactUseCases {
        return ContactUseCases(
            addContact = AddContact(repository),
            deleteContact = DeleteContact(repository),
            getAllContacts = GetAllContacts(repository),
            getSingleContact = GetSingleContact(repository),
            editContact = EditContact(repository)
        )
    }
}
