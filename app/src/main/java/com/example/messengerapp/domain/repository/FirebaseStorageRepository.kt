package com.example.messengerapp.domain.repository

import android.net.Uri

interface FirebaseStorageRepository {
    suspend fun uploadAndGetUrlImage(uri: Uri?) : Uri?
}