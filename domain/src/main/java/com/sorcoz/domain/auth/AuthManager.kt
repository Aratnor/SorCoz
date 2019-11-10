package com.sorcoz.domain.auth

import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import java.lang.Exception

interface AuthManager {
    interface LogoutCallBack {
        fun onLogoutSuccess()
        fun onLogoutFailed(exception: Exception)
    }

    suspend fun login(params: LoginWithTokenProvider.Params): Resource<User>

    suspend fun logout(LogoutCallBack: LogoutCallBack)

}