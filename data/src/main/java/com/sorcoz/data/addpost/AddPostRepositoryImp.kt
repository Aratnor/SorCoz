package com.sorcoz.data.addpost

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.sorcoz.domain.IDispatcherProvider
import com.sorcoz.domain.addpost.AddPostProvider
import com.sorcoz.domain.addpost.AddPostRepository
import com.sorcoz.domain.model.Post
import com.sorcoz.domain.model.Resource
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddPostRepositoryImp @Inject constructor(
    private val db: FirebaseFirestore,
    private val dispatcher: IDispatcherProvider
): AddPostRepository {
    override suspend fun addPost(params: AddPostProvider.Params): Resource<Post> {
                val task = db
                    .collection("posts")
                    .document("category1")
                    .collection(params.category1)
                    .document("category2")
                    .collection(params.category2)
                    .document("category3")
                    .collection(params.category3)
                    .document(params.post.postId)
                    .set(params.post)
        return try {
            withContext(dispatcher.io){
                task.await()
            }
            Resource.success(params.post)
        }catch (e: Exception) {
            Resource.error(e)
        }
    }
}