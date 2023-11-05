package com.example.messengerapp.domain.usecase

import android.net.Uri
import android.util.Log
import com.example.messengerapp.domain.repository.FirebaseAuthRepository
import com.example.messengerapp.presentation.common.resource.UIState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SignUpUseCase @Inject constructor
    (private val authRepository: FirebaseAuthRepository,
     private val savingUserProfileUseCase: SavingUserProfileUseCase)
{

    suspend operator fun invoke(email: String, password: String, fullname: String, images: Uri? = null) : UIState<String> {
        return try{
            if (email.trim().isEmpty())
                throw Exception("Email not valid")
            if (password.trim().isEmpty())
                throw Exception("Password not valid")
            if (fullname.trim().isEmpty())
                throw Exception("Name not valid")

            authRepository.signUpWithEmailAndPassword(email, password)
            savingUserProfileUseCase(fullname, images)



            UIState.Success("Sign up successfully")
        }catch (e: Exception){
            Log.e(TAG, "invoke: ${e.message}", )
            UIState.Failure(e.message ?: "Sign up failed. Please try again ...")
        }
    }

    companion object{
        private const val TAG = "SignUpAndSavingUseCase::class"
    }
}