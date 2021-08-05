package com.carlosmuvi.eventtracker.data.event

import com.google.firebase.Timestamp
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

private const val FIELD_DESCRIPTION = "description"
private const val FIELD_START_TIME = "start_time"
private const val FIELD_END_TIME = "end_time"

object EventMapper {
    fun Event.toFirestoreEvent(): Map<String, Any> {
        return hashMapOf(
            FIELD_DESCRIPTION to description,
            FIELD_START_TIME to startTime.toDate(),
            FIELD_END_TIME to endTime.toDate(),
        )
    }

    fun QueryDocumentSnapshot.toEvent(): Event {
        return Event(
            id = id,
            description = get(FIELD_DESCRIPTION) as String,
            startTime = (get(FIELD_START_TIME) as Timestamp).toDate().toDateTime(),
            endTime = (get(FIELD_END_TIME) as Timestamp).toDate().toDateTime()
        )
    }

    private fun Date.toDateTime(): LocalDateTime {
        return toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    private fun LocalDateTime.toDate(): Date {
        return Date.from(atZone(ZoneId.systemDefault()).toInstant())
    }


}