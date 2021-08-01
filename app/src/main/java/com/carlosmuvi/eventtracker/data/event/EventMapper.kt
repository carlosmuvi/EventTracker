package com.carlosmuvi.eventtracker.data.event

import com.google.firebase.Timestamp

private const val FIELD_DESCRIPTION = "description"
private const val FIELD_DATE = "date"

object EventMapper {
    fun Event.toFirestoreEvent(): Map<String, Any> {
        return hashMapOf(
            FIELD_DESCRIPTION to description,
            FIELD_DATE to date
        )
    }

    fun Map<String, Any>.toEvent(): Event {
        return Event(
            description = get(FIELD_DESCRIPTION) as String,
            date = (get(FIELD_DATE) as Timestamp).toDate()
        )
    }
}