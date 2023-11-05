package com.example.messengerapp.presentation.di

import com.example.messengerapp.data.repository.FirebaseAuthRepositoryImpl
import com.example.messengerapp.data.repository.FirebaseFirestoreRepositoryImpl
import com.example.messengerapp.data.repository.FirebaseStorageRepositoryImpl
import com.example.messengerapp.data.repository.FirebaseUserManageRepositoryImpl
import com.example.messengerapp.domain.repository.FirebaseAuthRepository
import com.example.messengerapp.domain.repository.FirebaseFirestoreRepository
import com.example.messengerapp.domain.repository.FirebaseStorageRepository
import com.example.messengerapp.domain.repository.FirebaseUserManageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFirebaseAuthRepository(authImpl: FirebaseAuthRepositoryImpl) : FirebaseAuthRepository
    @Binds
    abstract fun bindFirebaseFirestoreRepository(firestoreImpl: FirebaseFirestoreRepositoryImpl) : FirebaseFirestoreRepository
    @Binds
    abstract fun bindFirebaseStorageRepository(storageImpl: FirebaseStorageRepositoryImpl) : FirebaseStorageRepository
    @Binds
    abstract fun bindFirebaseUserManageRepository(userManageImpl: FirebaseUserManageRepositoryImpl) : FirebaseUserManageRepository
}