package com.darklabs.dailydose.di

import com.darklabs.businessdomain.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkSource() = NetworkClient.provideNetworkDataProvider()

}