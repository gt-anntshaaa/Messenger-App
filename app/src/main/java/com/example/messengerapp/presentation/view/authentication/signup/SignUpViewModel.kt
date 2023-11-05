package com.example.messengerapp.presentation.view.authentication.signup

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messengerapp.domain.usecase.SignUpUseCase
import com.example.messengerapp.presentation.common.resource.UIState
import com.example.messengerapp.presentation.common.util.SingleLiveEvent
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor
    (private val signUpUseCase: SignUpUseCase) : ViewModel(){

    private val _user = SingleLiveEvent<UIState<String>?>()
    val user get() = _user

    fun doSignUpAndCreateProfile(email: String, password: String, fullname: String, image: Uri? = null){
        _user.value = UIState.Loading
        viewModelScope.launch (Dispatchers.Main){
            _user.value = signUpUseCase(email, password, fullname, image)
        }
    }

    private val _imagePicked = MutableLiveData<Uri?>()
    val imagePicked get() = _imagePicked

    fun selectedProfileImage(imageUri: Uri){
        _imagePicked.value = imageUri
    }
}