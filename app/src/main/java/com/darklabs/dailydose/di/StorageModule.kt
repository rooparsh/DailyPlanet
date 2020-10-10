package com.darklabs.dailydose.di

import android.app.Application
import com.darklabs.businessdomain.db.database.NewsDatabase
import com.darklabs.businessdomain.db.database.dao.NewsArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): NewsDatabase = NewsDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase): NewsArticleDao = db.newsArticleDao()

}