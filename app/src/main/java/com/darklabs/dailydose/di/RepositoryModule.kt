package com.darklabs.dailydose.di

import com.darklabs.dailydose.domain.newsRepository.NewsRepository
import com.darklabs.dailydose.domain.newsRepository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    fun provideNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}