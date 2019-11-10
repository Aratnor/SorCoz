package com.sorcoz.andorid.addpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorcoz.domain.addpost.AddPostProvider
import com.sorcoz.domain.model.Post
import com.sorcoz.domain.model.Resource
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class AddPostViewModel @Inject constructor(
    private val addPostProvider: AddPostProvider
) : ViewModel() {

    val addPostState = MutableLiveData<Resource<Post>>()

    fun addPost(uid: String,desc: String,image_url: String,category1: String,category2: String,category3: String){
        viewModelScope.launch {
            val post = Post(
                UUID.randomUUID().toString(),
                uid,
                desc,
                image_url,
                Calendar.getInstance().time,
                ArrayList(listOf(category1,category2,category3))
            )
            addPostProvider.execute(
                AddPostProvider.Params(
                    category1,category2,category3,post
                )
            )
                .also { addPostState.value = it }
        }
    }
}