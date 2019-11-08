package com.sorcoz.domain.auth

import com.sorcoz.domain.model.User
import java.lang.Exception

interface AuthManager {
    interface LoginCallBack {
        fun onLoginSuccess(user: User)
        fun onLoginFailed(exception: Exception)
    }

    interface LogoutCallBack {
        fun onLogoutSuccess()
        fun onLogoutFailed(exception: Exception)
    }

    suspend fun login(token: String, authType: AuthType, LoginCallBack: LoginCallBack)

    suspend fun logout(LogoutCallBack: LogoutCallBack)
}