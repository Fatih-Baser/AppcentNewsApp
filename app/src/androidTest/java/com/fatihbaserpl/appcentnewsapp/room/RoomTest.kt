package com.fatihbaserpl.appcentnewsapp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.fatihbaserpl.appcentnewsapp.domain.Article
import com.fatihbaserpl.appcentnewsapp.favoriteroom.ArticleDao
import com.fatihbaserpl.appcentnewsapp.favoriteroom.ArticleDatabase
import com.fatihbaserpl.appcentnewsapp.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class RoomTest {

    @get :Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    // private lateinit var database: ArticleDatabase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @Named("testDatabase")
    lateinit var databases: ArticleDatabase
    private lateinit var dao: ArticleDao


    @Before
    fun setup() {
        /* database=Room.inMemoryDatabaseBuilder(
             ApplicationProvider.getApplicationContext(),ArticleDatabase::class.java
         ).allowMainThreadQueries().build()

         */
        hiltRule.inject()
        dao = databases.articleDAO()
    }

    @After
    fun teardown() {
        databases.close()
    }

    @Test
    @Throws(Exception::class)
    fun addFavoriteTesting() = runTest {
        val exampleArticle = Article(
            id = 1,
            author = "fatih",
            content = "xx",
            description = "fatih baser",
            publishedAt = "xxx",
            title = "besiktas",
            url = "www.google.com",
            urlToImage = "image.com"

        )

        /*val exampleArticleToList:Article=exampleArticle
        dao.upsert(exampleArticleToList)

        val list =dao.getAllArticles().getOrAwaitValue()
        assertThat(list).contains(exampleArticle)

         */


        dao.upsert(exampleArticle)

        val list = dao.getAllArticles().getOrAwaitValue()
        assertThat(list).contains(exampleArticle)

    }

}