package com.sorcoz.andorid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sorcoz.andorid.addpost.AddPostActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_post.setOnClickListener(View.OnClickListener {
            val next = Intent(this,AddPostActivity::class.java)
            next.putExtra("user_id",intent.getStringExtra("user_id"))
            startActivity(next)

        })
    }
}
