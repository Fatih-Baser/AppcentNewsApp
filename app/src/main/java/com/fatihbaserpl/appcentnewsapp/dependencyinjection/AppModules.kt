package com.fatihbaserpl.appcentnewsapp.dependencyinjection

import com.fatihbaserpl.appcentnewsapp.domain.NewsRepository
import com.fatihbaserpl.appcentnewsapp.remote.NewsInterface
import com.fatihbaserpl.appcentnewsapp.repository.NewsRepositoryImpl
import com.fatihbaserpl.appcentnewsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModules {

    @Singleton
    @Provides
    fun provideRetrofit(): NewsInterface {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NewsInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(newsInterface: NewsInterface): NewsRepository {
        return NewsRepositoryImpl(newsInterface)
    }

  //  @Singleton
    //@Provides
    //fun newsAdapter(): NewsAdapter = NewsAdapter()

}