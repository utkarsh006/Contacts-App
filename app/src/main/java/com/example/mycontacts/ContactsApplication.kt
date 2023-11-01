package com.example.mycontacts

import android.app.Application
import com.example.mycontacts.room.AppContainer
import com.example.mycontacts.room.AppDataContainer


class ContactsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}