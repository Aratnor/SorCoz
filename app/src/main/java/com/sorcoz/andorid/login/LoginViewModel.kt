package com.sorcoz.andorid.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorcoz.data.auth.AuthRepository
import com.sorcoz.domain.auth.AuthManager
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.auth.CurrentUserProvider
import com.sorcoz.domain.auth.LoginWithTokenProvider
import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginWithTokenProvider: LoginWithTokenProvider,
    private val currentUserProvider: CurrentUserProvider
) : ViewModel() {

    val loginState = MutableLiveData<Resource<User>>()

    fun login(token: String, type: AuthType) {
        viewModelScope.launch {
            loginWithTokenProvider
                .execute(
                    LoginWithTokenProvider.Params(token, type)
                )
                .also {
                    loginState.value = it
                }
        }
    }

    fun getCurrentUser(){
        viewModelScope.launch {
            val currentUser = currentUserProvider.execute()
            loginState.value = currentUser
        }
    }
}