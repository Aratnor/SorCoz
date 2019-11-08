package com.sorcoz.data.auth

import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.sorcoz.domain.auth.AuthManager
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.model.User

class AuthRepository constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthManager {
    private val TAG_GOOGLE = "Google check"
    private val TAG_FIREBASE = "Firebase check"
    override suspend fun login(
        token:String,
        authType: AuthType,
        LoginCallBack: AuthManager.LoginCallBack){
        try {
            var credential: AuthCredential? = null
            when(authType){
                AuthType.GOOGLE -> credential = GoogleAuthProvider.getCredential(token, null)

            }
            signInWithCredential(credential,LoginCallBack)
        } catch (e: ApiException) {
            Log.w(TAG_GOOGLE, "Google sign in failed", e)
            LoginCallBack.onLoginFailed(e)
        }
    }

    private fun signInWithCredential(
        credential: AuthCredential,
        LoginCallBack: AuthManager.LoginCallBack
        ){
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                if(task.result?.additionalUserInfo?.isNewUser!!) {
                    val currentUser = auth.currentUser!!
                    val newUser =User(
                        currentUser.uid,
                        currentUser.displayName.orEmpty(),
                        currentUser.email.orEmpty(),
                        currentUser.email.toString())
                    saveUser(newUser)
                    LoginCallBack.onLoginSuccess(newUser)
                }
                Log.d(TAG_GOOGLE, "signInWithCredential:success")
            } else {
                Log.w(TAG_GOOGLE, "signInWithCredential:failure", task.exception)
                LoginCallBack.onLoginFailed(task.exception!!)
            }
        }
    }

    private fun saveUser(user: User){
        db.collection("users")
            .document(user.uid)
            .set(user)
            .addOnSuccessListener { Log.d(TAG_FIREBASE, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG_FIREBASE, "Error writing document", e) }
    }

    override suspend fun logout(LogoutCallBack: AuthManager.LogoutCallBack) {

    }

    companion object {
        private var INSTANCE: AuthRepository? = null

        private val lock = Any()

        fun getInstance(): AuthRepository {
            synchronized(lock) {
                if(INSTANCE == null) {
                    val auth = FirebaseAuth.getInstance()
                    val db = FirebaseFirestore.getInstance()
                    INSTANCE =
                        AuthRepository(auth, db)
                }
                return INSTANCE!!
            }
        }



    }
}