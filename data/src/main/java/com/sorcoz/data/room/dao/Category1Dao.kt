package com.sorcoz.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sorcoz.data.room.dao.BaseDao
import com.sorcoz.data.room.model.Category1

@Dao interface Category1Dao: BaseDao<Category1> {

    @Query("SELECT * FROM category1_table")
    suspend fun getAllCategory1(): List<Category1>

    @Query("SELECT * FROM category1_table WHERE category1_name = :name")
    suspend fun getCategory1(name: String): Category1
}