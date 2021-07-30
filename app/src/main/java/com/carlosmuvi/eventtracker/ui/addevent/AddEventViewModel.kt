package com.carlosmuvi.eventtracker.ui.addevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.Event
import com.carlosmuvi.eventtracker.data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {

    fun save(description: String, navigateUp: () -> Unit) {
        viewModelScope.launch {
            eventRepository.save(Event(description = description, date = Date()))
            navigateUp()
        }
    }
}
