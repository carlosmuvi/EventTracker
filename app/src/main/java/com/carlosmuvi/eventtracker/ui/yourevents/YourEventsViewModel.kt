package com.carlosmuvi.eventtracker.ui.yourevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.Event
import com.carlosmuvi.eventtracker.data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class YourEventsViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    val state = MutableStateFlow(YourEventsState(emptyList()))

    init {
        viewModelScope.launch {
            eventRepository.observe().collect {
                state.value = state.value.copy(events = it)
            }
        }

    }
}

data class YourEventsState(val events: List<Event>)