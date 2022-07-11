package com.fatihbaserpl.appcentnewsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fatihbaserpl.appcentnewsapp.domain.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)

abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDAO(): ArticleDao

    companion object {
        private lateinit var INSTANCE: ArticleDatabase

        fun getDatabase(context: Context): ArticleDatabase {
            synchronized(ArticleDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "newsdatabase"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}