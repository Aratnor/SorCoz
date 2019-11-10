package com.sorcoz.domain.user

import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import com.sorcoz.domain.usecase.UseCase
import com.sorcoz.domain.usecase.UseCaseWithParams
import com.sorcoz.domain.user.IUserRepository
import javax.inject.Inject

class CurrentUserProvider @Inject constructor(private val userRepository: IUserRepository) :
    UseCase<Resource<User>>() {
    override suspend fun buildUseCase(): Resource<User> {
        return userRepository.getCurrentUser()
    }

}