package com.sorcoz.data.room.repository

import androidx.lifecycle.LiveData
import com.sorcoz.data.room.database.CategoryDatabase
import com.sorcoz.data.room.model.Category1
import com.sorcoz.data.room.model.Category2
import com.sorcoz.data.room.model.Category3
import javax.inject.Inject

class CategoryRepositoryImp @Inject constructor(
    private val database: CategoryDatabase
): CategoryRepository {
    override suspend fun getCategory1(name: String): Category1 {
        return database.category1Dao().getCategory1(name)
    }

    override suspend fun getAllCategory1(): List<Category1> {
        return database.category1Dao().getAllCategory1()
    }

    override suspend fun getAllCategory2(cat1_name: String): List<Category2> {
        return database.category2Dao().getAllCategory2ByCategory1(cat1_name)
    }

    override suspend fun getAllCategory3(cat2_name: String): List<Category3> {
        return database.category3Dao().getAllCategory3ByCategory2(cat2_name)
    }

    override suspend fun insertCategory1(category1: Category1) {
        database.category1Dao().insert(category1)
    }

    override suspend fun insertAllCategory1(list: List<Category1>) {
        database.category1Dao().insertAll(list)
    }
    override suspend fun insertCategory2(category2: Category2) {
       database.category2Dao().insert(category2)
    }

    override suspend fun insertAllCategory2(list: List<Category2>) {
        database.category2Dao().insertAll(list)
    }

    override suspend fun insertCategory3(category3: Category3) {
        database.category3Dao().insert(category3)
    }

    override suspend fun insertAllCategory3(category3: List<Category3>) {
        database.category3Dao().insertAll(category3)
    }


}