package com.example.messengerapp.presentation.view.authentication.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapp.domain.usecase.SignInUseCase
import com.example.messengerapp.presentation.common.resource.UIState
import com.example.messengerapp.presentation.common.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {
    private val _user = SingleLiveEvent<UIState<String>?>()
    val user get() = _user

    fun doSignIn(email: String, password: String){
        _user.value = UIState.Loading
        viewModelScope.launch (Dispatchers.Main) {
            _user.value = signInUseCase(email, password)
        }
    }
}