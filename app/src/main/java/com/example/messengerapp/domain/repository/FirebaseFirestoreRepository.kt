package com.example.messengerapp.domain.repository

import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.data.model.users.User

interface FirebaseFirestoreRepository {
    suspend fun save(data: User)
    suspend fun getDocumentUser() : User?
    suspend fun getDocumentContact() : List<Contact>
    suspend fun update(name: String)
}