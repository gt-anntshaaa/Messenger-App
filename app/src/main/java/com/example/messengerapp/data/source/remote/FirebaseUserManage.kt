package com.example.messengerapp.data.source.remote

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseUserManage @Inject constructor(private val auth: FirebaseAuth) {
    suspend fun updateProfile(name: String, imageUri: Uri?){
        val profileUpdates = userProfileChangeRequest {
            displayName = name
            photoUri = imageUri
        }

        withContext(Dispatchers.IO){
            auth.currentUser!!.updateProfile(profileUpdates).await()
        }
    }

    suspend fun updateProfile(name: String?){
        val profileNameUpdates = userProfileChangeRequest {
            displayName = name
        }
        withContext(Dispatchers.IO){
            auth.currentUser!!.updateProfile(profileNameUpdates)
        }
    }
}