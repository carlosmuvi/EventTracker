package com.carlosmuvi.eventtracker.data.event

import com.carlosmuvi.eventtracker.data.event.EventMapper.toEvent
import com.carlosmuvi.eventtracker.data.event.EventMapper.toFirestoreEvent
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface EventRepository {
    suspend fun save(event: Event)
    suspend fun getAll(): List<Event>
}

class EventRepositoryImpl @Inject constructor() : EventRepository {

    private val db = Firebase.firestore
    private val eventsCollection = db.collection("events")

    override suspend fun save(event: Event) {
        eventsCollection.add(event.toFirestoreEvent())
    }

    override suspend fun getAll(): List<Event> {
        return suspendCoroutine { cont ->
            eventsCollection.get()
                .addOnSuccessListener {
                    cont.resume(it.mapNotNull { document -> document.data.toEvent() })
                }
                .addOnFailureListener { cont.resumeWithException(it) }
        }
    }
}


