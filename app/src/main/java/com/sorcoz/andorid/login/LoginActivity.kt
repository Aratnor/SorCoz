package com.sorcoz.andorid.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.sorcoz.andorid.MainActivity
import com.sorcoz.andorid.R
import com.sorcoz.andorid.addpost.AddPostActivity
import com.sorcoz.domain.addpost.AddPostRepository
import com.sorcoz.domain.auth.AuthType
import com.sorcoz.domain.model.User
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel by lazy {
        ViewModelProviders
            .of(
                this,
                viewModelFactory
            )
            .get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializeGoogleButton()
        viewModelLoginObserver()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentUser()
    }

    private fun initializeGoogleButton() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        google_login_button.setOnClickListener {
            startSignInIntent()
        }
    }

    private fun login(token: String, authType: AuthType) {
        viewModel.login(token, authType)
    }

    private fun viewModelLoginObserver() {
        viewModel.loginState.observe(this, Observer {
            if(it.data != null) {
                navigateActivity(it.data!!)
            }
        })
    }

    private fun navigateActivity(user: User) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("user_id",user.uid)
        startActivity(intent)
    }

    private fun startSignInIntent() {
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
                login(account.idToken!!, AuthType.GOOGLE)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}
