package com.sorcoz.domain.model

import java.util.*
import kotlin.collections.ArrayList

data class Post(
    val postId: String,
    val userId: String,
    val postDesc: String,
    val imageUrl: String,
    val createdAt: Date,
    val categories: ArrayList<String>
)