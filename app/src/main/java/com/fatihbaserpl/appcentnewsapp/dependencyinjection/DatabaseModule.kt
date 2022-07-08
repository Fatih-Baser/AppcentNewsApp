package com.fatihbaserpl.appcentnewsapp.dependencyinjection

import android.content.Context
import com.fatihbaserpl.appcentnewsapp.favoriteroom.ArticleDao
import com.fatihbaserpl.appcentnewsapp.favoriteroom.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context):ArticleDatabase{
        return ArticleDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideDao(articleDatabase: ArticleDatabase)=articleDatabase.articleDAO()


}