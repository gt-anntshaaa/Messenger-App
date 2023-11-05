package com.example.messengerapp.domain.usecase

import android.net.Uri
import android.util.Log
import com.example.messengerapp.data.model.users.User
import com.example.messengerapp.domain.repository.FirebaseFirestoreRepository
import com.example.messengerapp.domain.repository.FirebaseStorageRepository
import com.example.messengerapp.domain.repository.FirebaseUserManageRepository
import javax.inject.Inject

class SavingUserProfileUseCase @Inject constructor
    (private val userManageRepository: FirebaseUserManageRepository,
     private val storageRepository: FirebaseStorageRepository,
     private val firestoreRepository: FirebaseFirestoreRepository,
     ) {

    suspend operator fun invoke(name: String, image: Uri?) : Boolean {
        return try{
            // get User have authentication
            val user = userManageRepository.getUser()
            // upload and get url image from storage firebase

            val imageUrl = storageRepository.uploadAndGetUrlImage(image)

            // store url image to firestore and save another data
            firestoreRepository.save(User(name, user?.email!!, imageUrl.toString()))

            // get data name and url image from firestore to storing to auth
            val docUser = firestoreRepository.getDocumentUser()
            // update user profile on auth
            docUser?.let {
                userManageRepository.updateProfile(it.name, Uri.parse(it.images)) }
//            if (user != null){
//
//                Log.i(TAG, "invoke: user profile is saved")
//            }else{
//                Log.e(TAG, "invoke: user not authenticated, data not saved", )
//            }
            
            true
        }catch (e: Exception){
            Log.e(TAG, "invoke: saving and update profile failed", e)
            false
        }
    }

    companion object{
        private const val TAG = "SavingUserProfileUseCase::class"
    }
}