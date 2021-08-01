package com.carlosmuvi.eventtracker.di

import com.carlosmuvi.eventtracker.data.EventRepository
import com.carlosmuvi.eventtracker.data.EventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonRepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindEventRepository(repositoryImpl: EventRepositoryImpl): EventRepository
}