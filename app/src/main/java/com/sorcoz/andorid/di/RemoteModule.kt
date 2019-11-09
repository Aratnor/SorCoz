package com.sorcoz.andorid.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sorcoz.data.auth.AuthRepository
import com.sorcoz.domain.auth.AuthManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Singleton
        @Provides
        @JvmStatic
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideFirebaseFirestore(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }

    @Binds
    abstract fun bindAuthManager(authRepository: AuthRepository): AuthManager
}