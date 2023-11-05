package com.example.messengerapp.data.repository

import android.util.Log
import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.data.model.users.User
import com.example.messengerapp.data.source.remote.FirebaseFirestoreData
import com.example.messengerapp.domain.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class FirebaseFirestoreRepositoryImpl @Inject constructor
    (private val firestoreSource: FirebaseFirestoreData) : FirebaseFirestoreRepository {

    override suspend fun save(data: User) {
        try{
            firestoreSource.save(data)
            Log.i(TAG, "save: saving data success")
        }catch (e: Exception){
            Log.e(TAG, "save: saving data failed", e)
        }
    }

    override suspend fun getDocumentUser(): User? {
        return try{
            Log.i(TAG, "getDocumentUser: get document user success")
            firestoreSource.getDocumentUser()
        }catch (e: Exception){
            Log.e(TAG, "getDocumentUser: can't get document user", e)
            throw Exception(e.message)
        }
    }

    override suspend fun getDocumentContact(): List<Contact> {
        return try {
            firestoreSource.getDocumentContact()
        }catch (e: Exception){
            Log.e(TAG, "getDocumentContact: ${e.message}", )
            emptyList<Contact>()
        }
    }

    override suspend fun update(name: String) {
        return try{
            Log.i(TAG, "updateName: update field name success")
            firestoreSource.update(name)
        }catch (e: Exception){
            Log.e(TAG, "updateName: can't update field name on firestore", e)
            throw Exception(e.message)
        }
    }

    companion object{
        private const val TAG = "FirebaseFirestoreRepository::class"
    }

}