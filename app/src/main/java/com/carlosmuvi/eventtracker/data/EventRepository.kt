package com.carlosmuvi.eventtracker.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface EventRepository {
    suspend fun save(event: Event)
    fun observe(): Flow<List<Event>>
}

class EventRepositoryImpl @Inject constructor() : EventRepository {

    private val events = MutableStateFlow<List<Event>>(emptyList())

    override suspend fun save(event: Event) {
        events.emit(events.value + event)
    }

    override fun observe(): Flow<List<Event>> {
        return events
    }
}