package com.example.messengerapp.domain.usecase

import android.util.Log
import com.example.messengerapp.domain.repository.FirebaseAuthRepository
import com.example.messengerapp.presentation.common.resource.UIState
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: FirebaseAuthRepository) {
    suspend operator fun invoke(email: String, password: String) : UIState<String>{
        return try{
            if (email.trim().isEmpty())
                throw Exception("Email not valid")
            if (password.trim().isEmpty())
                throw Exception("Password not valid")

            authRepository.signInWithEmailAndPassword(email, password)
            UIState.Success("Login success")
        }catch (e: Exception){
            Log.e(TAG, "invoke: ${e.message}", )
            UIState.Failure(e.message ?: "error when during login")
        }
    }

    companion object{
        private const val TAG = "SignInUseCase::class"
    }
}