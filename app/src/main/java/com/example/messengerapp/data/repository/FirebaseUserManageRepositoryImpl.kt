package com.example.messengerapp.data.repository

import android.net.Uri
import android.util.Log
import com.example.messengerapp.data.source.remote.FirebaseUserManage
import com.example.messengerapp.domain.repository.FirebaseUserManageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseUserManageRepositoryImpl @Inject constructor
    (private val firebaseUserManage: FirebaseUserManage) : FirebaseUserManageRepository
{

    override fun getUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override fun signOut() {
        return FirebaseAuth.getInstance().signOut()
    }

    override suspend fun updateProfile(name: String, imageUrl: Uri?) {
        try {
            firebaseUserManage.updateProfile(name, imageUrl)
            Log.i(TAG, "updateProfile: update user profile success")
        }catch (e: Exception){
            Log.e(TAG, "updateProfile: can't update user profile", e)
        }
    }

    override suspend fun updateProfile(name: String?) {
        try {
            firebaseUserManage.updateProfile(name)
            Log.i(TAG, "updateProfile: update user name profile success")
        }catch (e: Exception){
            Log.e(TAG, "updateProfile: can't update user name profile", e)
        }
    }

    companion object{
        private const val TAG = "FirebaseUserManageRepository::class"
    }

}