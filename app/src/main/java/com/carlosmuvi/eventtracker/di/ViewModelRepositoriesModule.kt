package com.carlosmuvi.eventtracker.di

import com.carlosmuvi.eventtracker.data.AccountRepository
import com.carlosmuvi.eventtracker.data.AccountRepositoryImpl
import com.carlosmuvi.eventtracker.data.EventRepository
import com.carlosmuvi.eventtracker.data.EventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class AccountRepositoryModule {

    @Binds
    abstract fun bindAccountRepository(repositoryImpl: AccountRepositoryImpl): AccountRepository
}