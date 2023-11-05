package com.example.messengerapp.domain.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

interface FirebaseUserManageRepository {
    fun getUser() : FirebaseUser?
    fun signOut()
    suspend fun updateProfile(name: String, imageUrl: Uri? = null)
    suspend fun updateProfile(name: String?)
}