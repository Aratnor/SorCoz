package com.lambadam.data

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.sorcoz.data.room.database.CategoryDatabase
import com.sorcoz.data.room.database.CategoryDatabase_Impl
import com.sorcoz.data.room.model.Category1
import com.sorcoz.data.room.repository.CategoryProvider
import com.sorcoz.data.room.repository.CategoryRepositoryImp
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


class RoomDatabaseTest {
    @Inject lateinit var categoryProvider: CategoryProvider

    @Before
    fun prepare() {
    }

    @Test
    fun testSetAllData() = runBlocking {

        categoryProvider.setCategory1("geometri")
        println(getCategory1().category1_name)
        assert("geometri" == getCategory1().category1_name)
    }
    fun getCategory1(): Category1 = runBlocking {
        categoryProvider.getCategory1("geometri")
    }
}