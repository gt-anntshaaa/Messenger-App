package com.example.messengerapp.domain.usecase

import android.net.Uri
import com.example.messengerapp.domain.repository.FirebaseFirestoreRepository
import com.example.messengerapp.domain.repository.FirebaseUserManageRepository
import com.example.messengerapp.presentation.common.resource.UIState
import javax.inject.Inject

class UpdateNameProfileUseCase @Inject constructor
    (private val firestoreRepository: FirebaseFirestoreRepository,
     private val userManageRepository: FirebaseUserManageRepository) {

    suspend operator fun invoke(name: String) : UIState<String>{
        return try{
            if (name.trim().isEmpty())
                throw Exception("name not valid")

            firestoreRepository.update(name)
            val userUpdate = firestoreRepository.getDocumentUser()
            userManageRepository.updateProfile(userUpdate?.name)

            UIState.Success("update name successful")
        }catch (e: Exception){
            UIState.Failure(e.message ?: "error when during update field name")
        }
    }
}