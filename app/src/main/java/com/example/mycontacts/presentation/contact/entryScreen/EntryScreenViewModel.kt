package com.example.mycontacts.presentation.contact.entryScreen

import androidx.lifecycle.ViewModel
import com.example.mycontacts.domain.usecases.ContactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases
): ViewModel() {
}