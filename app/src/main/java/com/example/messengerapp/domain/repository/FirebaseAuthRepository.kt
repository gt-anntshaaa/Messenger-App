package com.example.messengerapp.domain.repository

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthRepository {
    suspend fun signUpWithEmailAndPassword(email: String, password: String) : FirebaseUser?
    suspend fun signInWithEmailAndPassword(email: String, password: String)
}