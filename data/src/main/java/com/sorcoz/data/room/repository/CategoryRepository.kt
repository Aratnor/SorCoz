package com.sorcoz.data.room.repository

import androidx.lifecycle.LiveData
import com.sorcoz.data.room.model.Category1
import com.sorcoz.data.room.model.Category2
import com.sorcoz.data.room.model.Category3
import javax.inject.Inject

interface CategoryRepository  {
    suspend fun getAllCategory1(): List<Category1>

    suspend fun getCategory1(name: String): Category1

    suspend fun getAllCategory2(cat1_name: String): List<Category2>

    suspend fun getAllCategory3(cat2_name: String): List<Category3>

    suspend fun insertCategory1(category1: Category1)

    suspend fun insertAllCategory1(list: List<Category1>)

    suspend fun insertCategory2(category2: Category2)

    suspend fun insertAllCategory2(list: List<Category2>)

    suspend fun insertCategory3(category3: Category3)

    suspend fun insertAllCategory3(category3: List<Category3>)

}