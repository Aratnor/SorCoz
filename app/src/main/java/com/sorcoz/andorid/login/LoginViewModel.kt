package com.sorcoz.andorid.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorcoz.data.auth.AuthRepository
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.auth.LoginWithTokenProvider
import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import kotlinx.coroutines.launch

class LoginViewModel constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val loginState = MutableLiveData<Resource<User>>()

    fun login(token: String, type: AuthType) {

        viewModelScope.launch {
            authRepository.login(LoginWithTokenProvider.Params(token, type)).also {
                loginState.value = it
            }
        }
    }
}