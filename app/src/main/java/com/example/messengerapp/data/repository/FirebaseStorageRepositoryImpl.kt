package com.example.messengerapp.data.repository

import android.net.Uri
import android.util.Log
import com.example.messengerapp.data.source.remote.FirebaseStorageData
import com.example.messengerapp.domain.repository.FirebaseStorageRepository
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject
    constructor(private val storageSource: FirebaseStorageData) : FirebaseStorageRepository {

    override suspend fun uploadAndGetUrlImage(uri: Uri?): Uri? {
        return if (uri == null) null else storageSource.uploadAndGetUrlImage(uri)
//        return try {
////            Log.i(TAG, "uploadAndGetUrlImage: upload image to storage firebase success")
////            storageSource.uploadAndGetUrlImage(uri)
//
//        }catch (e: Exception){
//            Log.e(TAG, "uploadAndGetUrlImage: upload image to storage firebase failed", e)
//            throw Exception(e.message)
//        }
    }

    companion object{
        private const val TAG = "FirebaseStorageRepository::class"
    }

}