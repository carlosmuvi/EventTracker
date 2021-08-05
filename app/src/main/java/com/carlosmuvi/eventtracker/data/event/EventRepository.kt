package com.carlosmuvi.eventtracker.data.event

import com.carlosmuvi.eventtracker.data.account.UserManager
import com.carlosmuvi.eventtracker.data.event.EventMapper.toEvent
import com.carlosmuvi.eventtracker.data.event.EventMapper.toFirestoreEvent
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface EventRepository {
    suspend fun save(event: Event)
    suspend fun getAll(): List<Event>
}

class EventRepositoryImpl @Inject constructor(
    userManager: UserManager,
    firestore: FirebaseFirestore
) : EventRepository {

    private val eventsCollection = firestore
        .collection("users")
        .document(userManager.currentUser!!.uid)
        .collection("events")

    override suspend fun save(event: Event) {
        eventsCollection.add(event.toFirestoreEvent())
    }

    override suspend fun getAll(): List<Event> {
        val eventsCollection = eventsCollection
        return suspendCoroutine { cont ->
            eventsCollection.get()
                .addOnSuccessListener {
                    cont.resume(it.mapNotNull { document -> document.toEvent() })
                }
                .addOnFailureListener { cont.resumeWithException(it) }
        }
    }
}


