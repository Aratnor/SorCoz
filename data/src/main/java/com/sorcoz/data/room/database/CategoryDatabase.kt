package com.sorcoz.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sorcoz.data.room.dao.Category1Dao
import com.sorcoz.data.room.dao.Category2Dao
import com.sorcoz.data.room.dao.Category3Dao
import com.sorcoz.data.room.model.Category1
import com.sorcoz.data.room.model.Category2
import com.sorcoz.data.room.model.Category3

@Database(entities = [Category1::class, Category2::class, Category3::class],version = 1,exportSchema = false)
abstract class CategoryDatabase: RoomDatabase() {
    abstract fun category1Dao(): Category1Dao
    abstract fun category2Dao(): Category2Dao
    abstract fun category3Dao(): Category3Dao

    /*companion object {
        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        fun getInstance(context: Context): CategoryDatabase {
            if(INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CategoryDatabase::class.java,
                    "category_database"
                ).build()
                return INSTANCE!!
            }
        }
    }*/
}