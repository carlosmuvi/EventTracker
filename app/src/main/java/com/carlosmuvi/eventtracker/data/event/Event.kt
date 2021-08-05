package com.carlosmuvi.eventtracker.data.event

import java.time.LocalDateTime

data class Event(
    val id: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
