package com.example.mycontacts.presentation.contact.editScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mycontacts.domain.usecases.ContactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

}