package com.sorcoz.domain.addpost

import com.sorcoz.domain.model.Post
import com.sorcoz.domain.model.Resource
import com.sorcoz.domain.usecase.UseCaseWithParams
import javax.inject.Inject

class AddPostProvider @Inject constructor(
    private val addPostRepository: AddPostRepository):
    UseCaseWithParams<AddPostProvider.Params, Resource<Post>>() {
    override suspend fun buildUseCase(params: Params): Resource<Post> {
        return addPostRepository.addPost(params)
    }

    data class Params(
        val category1: String,
        val category2 : String,
        val category3 : String,
        val post: Post)
}