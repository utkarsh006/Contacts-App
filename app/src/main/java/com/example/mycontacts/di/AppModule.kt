package com.example.mycontacts.di

import android.app.Application
import androidx.room.Room
import com.example.mycontacts.data.dataSource.ContactsDatabase
import com.example.mycontacts.data.repository.OfflineContactsRepository
import com.example.mycontacts.domain.repository.ContactsRepository
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

}