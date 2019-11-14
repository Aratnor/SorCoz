package com.sorcoz.data.room.repository

import androidx.lifecycle.LiveData
import com.sorcoz.data.room.model.Category1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject


class CategoryProvider @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

   private val tytCategories = listOf(
        "geometri","turkce","matematik","cografya",
        "tarih","din-kulturu","felsefe","kimya","fizik","biyoloji")
    private val baseUrl= "https://benimhedefim.net/tyt-"

    suspend fun setCategory1(name: String){
        val res = Category1(name)
        categoryRepository.insertCategory1(res)
    }

    suspend fun getAllCategories1(): List<Category1>{
        return categoryRepository.getAllCategory1()
    }
    suspend fun getCategory1(name: String): Category1 {
        return categoryRepository.getCategory1(name)
    }

    fun getCategories(url: String): ArrayList<String> {
        val document = Jsoup.connect(url).get()

        val resultSet = ArrayList<String>()

        val table = document.select("div.tie-list-shortcode")
        val elements = table.select("li")
        for (element in elements) {
            var res = element.text()
            res = testRegex(res).trim { it <= ' ' }
            if(res.contains("TYT"))continue
            resultSet.add(res)
        }
        return resultSet
    }
    fun getTyt()= tytCategories

    private fun testRegex(res: String): String {
        return res.replace("[^a-zA-Z\\s\\p{L}]".toRegex(), "")
    }
}