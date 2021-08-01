package com.carlosmuvi.eventtracker.di

import com.carlosmuvi.eventtracker.data.AccountRepository
import com.carlosmuvi.eventtracker.data.AccountRepositoryImpl
import com.carlosmuvi.eventtracker.data.event.EventRepository
import com.carlosmuvi.eventtracker.data.event.EventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AccountRepositoryModule {

    @Binds
    abstract fun bindAccountRepository(repositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindEventRepository(repositoryImpl: EventRepositoryImpl): EventRepository
}