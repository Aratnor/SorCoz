package com.sorcoz.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sorcoz.data.room.dao.BaseDao
import com.sorcoz.data.room.model.Category2

@Dao interface Category2Dao : BaseDao<Category2> {

    @Query("SELECT * FROM category2_table WHERE cat1_name = :cat1_name")
    suspend fun getAllCategory2ByCategory1(cat1_name: String): List<Category2>

    @Query("SELECT * FROM category2_table")
    suspend fun getAllCategory2(): List<Category2>

}