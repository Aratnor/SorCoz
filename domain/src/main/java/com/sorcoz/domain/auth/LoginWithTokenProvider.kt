package com.sorcoz.domain.auth

import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import com.sorcoz.domain.usecase.UseCaseWithParams

class LoginWithTokenProvider(private val authManager: AuthManager) :
    UseCaseWithParams<LoginWithTokenProvider.Params, Resource<User>>() {

    override suspend fun buildUseCase(params: Params): Resource<User> {
        return authManager.login(params)
    }

    data class Params(val token: String, val authType: AuthType)
}