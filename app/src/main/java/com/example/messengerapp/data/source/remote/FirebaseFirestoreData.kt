package com.example.messengerapp.data.source.remote

import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.data.model.users.User
import com.example.messengerapp.presentation.common.util.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseFirestoreData @Inject constructor
    (private val firestore: FirebaseFirestore, private val auth: FirebaseAuth)
{

    suspend fun save(data: User){
        withContext(Dispatchers.IO){
            firestore.collection(Constant.REFERENCE.USER.name)
                .document(auth.currentUser!!.uid).set(data).await()
        }
    }

    suspend fun update(name: String){
        withContext(Dispatchers.IO){
            firestore.collection(Constant.REFERENCE.USER.name)
                .document(auth.currentUser!!.uid)
                .update("name", name)
                .await()
        }
    }

    suspend fun getDocumentUser() : User?{
        return withContext(Dispatchers.IO){
            firestore.collection(Constant.REFERENCE.USER.name)
                .document(auth.currentUser!!.uid)
                .get().await().toObject<User>()
        }
    }

    suspend fun getDocumentContact() : List<Contact>{
        val allContact = withContext(Dispatchers.IO){
            firestore.collection(Constant.REFERENCE.USER.name)
                .document(auth.currentUser!!.uid)
                .collection(Constant.REFERENCE.CONTACT.name)
                .get().await()
        }

        val listContact = mutableListOf<Contact>()
        for (contact in allContact){
            val contacts = contact.toObject<Contact>()
            listContact.add(contacts)
        }

        return listContact
    }
}