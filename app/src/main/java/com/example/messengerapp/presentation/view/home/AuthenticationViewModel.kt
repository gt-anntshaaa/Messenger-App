package com.example.messengerapp.presentation.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messengerapp.domain.repository.FirebaseUserManageRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor
    (private val userManageRepository: FirebaseUserManageRepository) : ViewModel(){

    private val _user = MutableLiveData<FirebaseUser?>()
    val user get() = _user

    fun checkAuthenticationUser(){
        val users = userManageRepository.getUser()
        _user.value = users
    }

    fun signOut(){
        userManageRepository.signOut()
    }
}