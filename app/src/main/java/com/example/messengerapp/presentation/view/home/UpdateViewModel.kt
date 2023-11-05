package com.example.messengerapp.presentation.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapp.domain.usecase.UpdateNameProfileUseCase
import com.example.messengerapp.presentation.common.resource.UIState
import com.example.messengerapp.presentation.common.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor
    (private val updateNameUseCase: UpdateNameProfileUseCase) : ViewModel()
{
    private val _updateName = SingleLiveEvent<UIState<String>?>()
    val updateName get() = _updateName

    fun doUpdateName(name: String){
        viewModelScope.launch (Dispatchers.IO) {
            _updateName.value = updateNameUseCase(name)
        }
    }
}