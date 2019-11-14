package com.sorcoz.data.room.di

import android.app.Application
import androidx.room.Room
import com.sorcoz.data.room.dao.Category1Dao
import com.sorcoz.data.room.dao.Category2Dao
import com.sorcoz.data.room.dao.Category3Dao
import com.sorcoz.data.room.database.CategoryDatabase
import com.sorcoz.data.room.repository.CategoryProvider
import com.sorcoz.data.room.repository.CategoryRepository
import com.sorcoz.data.room.repository.CategoryRepositoryImp
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideCategoryDatabase(application: Application): CategoryDatabase {
        return Room.databaseBuilder(
            application,
            CategoryDatabase::class.java,
            "category.db"
        ).build()
    }

    @Provides
    fun provideCategory1Dao(categoryDatabase: CategoryDatabase): Category1Dao {
        return categoryDatabase.category1Dao()
    }

    @Provides
    fun provideCategory2Dao(categoryDatabase: CategoryDatabase): Category2Dao {
        return categoryDatabase.category2Dao()
    }


    @Provides
    fun provideCategory3Dao(categoryDatabase: CategoryDatabase): Category3Dao {
        return categoryDatabase.category3Dao()
    }



}