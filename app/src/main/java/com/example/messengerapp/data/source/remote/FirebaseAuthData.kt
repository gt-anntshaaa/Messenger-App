package com.example.messengerapp.data.source.remote

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthData @Inject constructor(private val auth: FirebaseAuth){

    suspend fun signUpWithEmailAndPassword(email: String, password: String) : FirebaseUser?{
        return withContext(Dispatchers.IO){
            auth.createUserWithEmailAndPassword(email, password).await().user
        }
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String){
        withContext(Dispatchers.IO){
            auth.signInWithEmailAndPassword(email, password).await()
        }
    }
}