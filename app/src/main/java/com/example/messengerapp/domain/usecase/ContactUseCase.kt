package com.example.messengerapp.domain.usecase

import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.domain.repository.FirebaseFirestoreRepository
import com.example.messengerapp.presentation.common.resource.UIState
import javax.inject.Inject

class ContactUseCase @Inject constructor(private val firestoreRepository: FirebaseFirestoreRepository) {
    suspend operator fun invoke() : UIState<Pair<String, List<Contact>>>{
        return try{
            val listContact = firestoreRepository.getDocumentContact()
            if (listContact.isEmpty())
                throw Exception("No contact found")

            UIState.Success(Pair("", listContact))
        }catch (e: Exception){
            UIState.Failure(e.message ?: "no contact found")
        }
    }
}