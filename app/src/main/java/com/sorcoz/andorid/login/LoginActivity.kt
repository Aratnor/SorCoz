package com.sorcoz.andorid.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.sorcoz.andorid.R
import com.sorcoz.data.auth.AuthRepository
import com.sorcoz.domain.auth.AuthManager
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.model.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class LoginActivity : AppCompatActivity(), CoroutineScope {
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job
    private val viewModel by lazy {
        ViewModelProviders.of(this,
            LoginViewModelFactory.getInstance(applicationContext))
            .get(LoginViewModel::class.java)
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializeGoogleButton()
        viewModelLoginObserver()
    }

    private fun initializeGoogleButton() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        google_login_button.setOnClickListener {
            signIn()
        }
    }

    private fun login(token: String,authType: AuthType) {
        viewModel.login(token,authType)
    }

    private fun viewModelLoginObserver() {
        viewModel.loginState.observe(this, Observer {
            Log.d(TAG, "login state: $it")
        })
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                launch {
                    login(account.idToken!!,AuthType.GOOGLE)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}
