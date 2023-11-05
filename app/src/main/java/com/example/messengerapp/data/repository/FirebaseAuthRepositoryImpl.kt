package com.example.messengerapp.data.repository

import android.util.Log
import com.example.messengerapp.data.source.remote.FirebaseAuthData
import com.example.messengerapp.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor
    (private val authSource: FirebaseAuthData) : FirebaseAuthRepository
{

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return try{
            Log.i(TAG, "signUpWithEmailAndPassword: create account with email and password success")
            authSource.signUpWithEmailAndPassword(email, password)
        }catch (e: Exception){
            Log.e(TAG, "signUpWithEmailAndPassword: create account with email and password failed", e)
            throw Exception(e.message)
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        return try{
            Log.i(TAG, "signInWithEmailAndPassword: sign in success")
            authSource.signInWithEmailAndPassword(email, password)
        }catch (e: Exception){
            Log.e(TAG, "signInWithEmailAndPassword: error when during sign in", e)
            throw Exception(e.message)
        }
    }

    companion object{
        private const val TAG = "FirebaseAuthRepository::class"
    }

}