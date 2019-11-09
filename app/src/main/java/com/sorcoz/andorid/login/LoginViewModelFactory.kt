package com.sorcoz.andorid.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sorcoz.data.auth.AuthRepository

class LoginViewModelFactory(
    private val authRepository: AuthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(LoginViewModel::class.java))
            throw IllegalArgumentException("Model class must be LoginViewModel")

        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(authRepository) as T
    }

    companion object {

        private var INSTANCE: LoginViewModelFactory? = null

        fun getInstance(context: Context): LoginViewModelFactory{
            return INSTANCE ?: synchronized(this){
                val repo = AuthRepository.getInstance()
                INSTANCE ?: LoginViewModelFactory(repo)
                    .also { INSTANCE = it }
            }
        }
    }

}