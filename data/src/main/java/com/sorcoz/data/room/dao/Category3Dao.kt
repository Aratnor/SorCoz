package com.sorcoz.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sorcoz.data.room.model.Category3

@Dao
interface Category3Dao: BaseDao<Category3> {

    @Query("SELECT * FROM category3_table WHERE cat2_name = :cat2_name")
    suspend fun getAllCategory3ByCategory2(cat2_name: String): List<Category3>

    @Query("SELECT * FROM category3_table")
    suspend fun getAllCategory3(): List<Category3>
}