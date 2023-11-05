package com.example.messengerapp.data.source.remote

import android.net.Uri
import com.example.messengerapp.presentation.common.util.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseStorageData @Inject constructor(private val storage: FirebaseStorage, private val auth: FirebaseAuth) {
    suspend fun uploadAndGetUrlImage(uri : Uri?) : Uri?{
        return withContext(Dispatchers.IO){
            storage.reference.child(Constant.REFERENCE.IMAGES.name)
                .child(auth.currentUser!!.uid).putFile(uri!!).await()
                .storage.downloadUrl.await()
        }
    }
}