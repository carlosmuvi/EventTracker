package com.carlosmuvi.eventtracker.ui.addevent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.event.Event
import com.carlosmuvi.eventtracker.data.event.EventRepository
import com.carlosmuvi.eventtracker.data.person.Person
import com.carlosmuvi.eventtracker.data.person.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository
) : ViewModel() {

    val state = MutableStateFlow(AddEventState())

    init {
        viewModelScope.launch {
            kotlin.runCatching { personRepository.getAll() }
                .onSuccess { state.value = state.value.copy(persons = it) }
                .onFailure { Log.e("ERROR", "couldn't fetch people", it) }
        }
    }

    fun save(form: AddEventForm, navigateUp: () -> Unit) {
        viewModelScope.launch {
            eventRepository.save(
                Event(
                    id = "",
                    description = form.description,
                    startTime = LocalDateTime.of(LocalDate.now(), form.dateRange.first),
                    endTime = LocalDateTime.of(LocalDate.now(), form.dateRange.second)
                )
            )
            navigateUp()
        }
    }
}

data class AddEventState(
    val persons: List<Person> = emptyList()
)
