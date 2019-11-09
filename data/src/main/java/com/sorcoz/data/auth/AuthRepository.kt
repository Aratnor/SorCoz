package com.sorcoz.data.auth

import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.sorcoz.domain.IDispatcherProvider
import com.sorcoz.domain.auth.AuthManager
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.auth.LoginWithTokenProvider
import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val dispatchers: IDispatcherProvider
) : AuthManager {

    override suspend fun login(params: LoginWithTokenProvider.Params): Resource<User> {
        return withContext(dispatchers.io) {
            try {
                val credential: AuthCredential = when (params.authType) {
                    AuthType.GOOGLE -> GoogleAuthProvider.getCredential(params.token, null)
                }
                signInWithCredential(credential)
            } catch (e: ApiException) {
                Resource.error<User>(e)
            }
        }
    }

    private suspend fun signInWithCredential(
        credential: AuthCredential
    ): Resource<User> {
        try {
            val authResult = auth.signInWithCredential(credential).await()
            val user = authResult.user ?: return Resource.error(Throwable())
            val newUser = User(
                user.uid,
                user.displayName.orEmpty(),
                user.email.orEmpty(),
                user.photoUrl?.toString().orEmpty()
            )

            return if (authResult.additionalUserInfo?.isNewUser == false) {
                Resource.success(newUser)
            } else {
                saveUser(newUser)
            }
        } catch (e: Exception) {
            return Resource.error(e)
        }
    }

    private suspend fun saveUser(user: User): Resource<User> {
        val task = db.collection("users")
            .document(user.uid)
            .set(user)
        return try {
            task.await()
            Resource.success(user)
        } catch (e: Exception) {
            Resource.error(e)
        }
    }

    override suspend fun logout(LogoutCallBack: AuthManager.LogoutCallBack) {}
}