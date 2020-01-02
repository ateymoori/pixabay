package com.pixabay

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pixabay.repo.db.ImagesDAO
import com.pixabay.utils.DBHelper
import com.pixabay.utils.entities.ImageModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
open class TestDatabase {

    private lateinit var database: DBHelper
    private lateinit var dao: ImagesDAO
    lateinit var context: Context

    @Before
    fun initDb() {
        context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(
            context,
            DBHelper::class.java
        ).build()
        dao = database.getImagesDAO()
    }

    @Test
    fun testInsert() {
        dao.removeAll()
        dao.insert(getFakeImageModel())
        assertTrue(dao.getAll().size == 1)
    }

    @Test
    fun testInsertAll() {
        dao.removeAll()
        dao.insertAll(
            listOf(getFakeImageModel(), getFakeImageModel(), getFakeImageModel())
        )
        assertTrue(dao.getAll().size == 3)
    }

    @Test
    fun testRemoveAll() {
        dao.removeAll()
        dao.insertAll(
            listOf(getFakeImageModel(), getFakeImageModel(), getFakeImageModel())
        )
        dao.removeAll()
        assertTrue(dao.getAll().isEmpty())
    }

    @Test
    fun testSearchByKeyWord() {
        dao.removeAll()
        dao.insert(getFakeImageModel("cat"))
        assertTrue(dao.getImagesBySearchWord("cat").size == 1)
    }

    private fun getFakeImageModel(searchWord: String? = null): ImageModel {
        return ImageModel(
            searchWord = searchWord,
            id = (0..100000).random()
        )
    }

    @After
    fun closeDb() {
        database.close()
    }

}