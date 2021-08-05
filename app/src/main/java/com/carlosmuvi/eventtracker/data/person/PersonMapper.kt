package com.carlosmuvi.eventtracker.data.person

import com.google.firebase.firestore.QueryDocumentSnapshot

private const val FIELD_NAME = "name"

object PersonMapper {
    fun Person.toFirestorePerson(): Map<String, Any> {
        return hashMapOf(
            FIELD_NAME to name,
        )
    }

    fun QueryDocumentSnapshot.toPerson(): Person {
        return Person(
            id = id,
            name = get(FIELD_NAME) as String,
        )
    }
}