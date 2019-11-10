package com.sorcoz.domain.addpost

import com.sorcoz.domain.model.Post
import com.sorcoz.domain.model.Resource

interface AddPostRepository {
    suspend fun addPost(params: AddPostProvider.Params): Resource<Post>
}