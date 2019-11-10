package com.sorcoz.data.user

import com.google.firebase.auth.FirebaseAuth
import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import com.sorcoz.domain.user.IUserRepository
import java.lang.NullPointerException
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val auth: FirebaseAuth
) : IUserRepository {
    override suspend fun getCurrentUser(): Resource<User> {
        val currentUser = auth.currentUser ?: return Resource.error(NullPointerException("FirebaseUserNull"))
        return Resource.success(
            User(currentUser.uid,
                currentUser.displayName.orEmpty(),
                currentUser.email.orEmpty(),
                currentUser.photoUrl.toString()))
    }
}