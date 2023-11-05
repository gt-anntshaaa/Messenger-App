package com.example.messengerapp.presentation.view.home.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.domain.usecase.ContactUseCase
import com.example.messengerapp.presentation.common.resource.UIState
import com.example.messengerapp.presentation.common.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactUseCase: ContactUseCase) : ViewModel() {
    private val _allContact = SingleLiveEvent<UIState<Pair<String, List<Contact>>>?>()
    val allContact get() = _allContact

    fun loadContact(){
        viewModelScope.launch {
            _allContact.value = contactUseCase()
        }
    }
}