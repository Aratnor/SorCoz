package com.sorcoz.andorid.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorcoz.data.auth.AuthRepository
import com.sorcoz.domain.auth.AuthManager
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.model.User
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    val loginSuccess = MutableLiveData<Boolean>()

    val loginFailure = MutableLiveData<Exception>()

    var currentUser: User? = null

    init {
        loginSuccess.value =false
    }

    fun login(token:String,type: AuthType){
        viewModelScope.launch {
            authRepository.login(token,type,object : AuthManager.LoginCallBack {
                override fun onLoginSuccess(user: User) {
                    loginSuccess.value = true
                    currentUser = user
                }

                override fun onLoginFailed(exception: Exception) {
                   loginFailure.value = exception
                }
            })
        }
    }


}