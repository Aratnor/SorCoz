package com.sorcoz.domain.user

import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User

interface IUserRepository {
    suspend fun getCurrentUser(): Resource<User>
}