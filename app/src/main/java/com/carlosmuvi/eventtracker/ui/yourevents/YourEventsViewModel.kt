package com.carlosmuvi.eventtracker.ui.yourevents

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.event.Event
import com.carlosmuvi.eventtracker.data.event.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourEventsViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    val state = MutableStateFlow(YourEventsState(emptyList()))

    init {
        viewModelScope.launch {
            runCatching { eventRepository.getAll() }
                .onSuccess { state.value = state.value.copy(events = it) }
                .onFailure { Log.e("ERROR", "Error retrieving events", it) }
        }
    }
}

data class YourEventsState(val events: List<Event>)