package com.lambadam.data

import com.sorcoz.data.room.database.CategoryDatabase
import com.sorcoz.data.room.repository.CategoryProvider
import org.junit.Test
import javax.inject.Inject

class CategoryProviderTest {
    @Inject
    lateinit var db: CategoryDatabase

    @Test
    fun testDb() {
        assert(db != null)
    }
}