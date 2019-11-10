package com.sorcoz.domain.auth

import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import com.sorcoz.domain.usecase.UseCase
import com.sorcoz.domain.usecase.UseCaseWithParams
import javax.inject.Inject

class CurrentUserProvider @Inject constructor(private val authManager: AuthManager) :
    UseCase<Resource<User>>() {
    override suspend fun buildUseCase(): Resource<User> {
        return authManager.getCurrentUser()
    }

}