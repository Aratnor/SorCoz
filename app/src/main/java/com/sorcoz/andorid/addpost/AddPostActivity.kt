package com.sorcoz.andorid.addpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sorcoz.andorid.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_post.*
import javax.inject.Inject

class AddPostActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders
            .of(
                this,
                viewModelFactory
            )
            .get(AddPostViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        apply_button.setOnClickListener(View.OnClickListener {
            val uid = intent.getStringExtra("user_id")!!
            viewModel.addPost(uid,editText.text.toString(),editText2.text.toString(),"cat1","cat2","cat3")
        })
        //viewModelAddPostObserver()

    }

    private fun viewModelAddPostObserver() {
        viewModel.addPostState.observe(this, Observer {
            val uid = intent.getStringExtra("user_id")
        })
    }
    companion object {
        private const val TAG = "AddPostActivity"
    }
}
