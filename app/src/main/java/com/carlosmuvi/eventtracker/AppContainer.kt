package com.carlosmuvi.eventtracker

import android.content.Context
import com.carlosmuvi.eventtracker.data.EventRepository
import com.carlosmuvi.eventtracker.data.EventRepositoryImpl
import com.carlosmuvi.eventtracker.data.PersonRepository
import com.carlosmuvi.eventtracker.data.PersonRepositoryImpl

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val eventRepository: EventRepository
    val personRepository: PersonRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val eventRepository: EventRepository by lazy { EventRepositoryImpl() }
    override val personRepository: PersonRepository by lazy { PersonRepositoryImpl() }
}