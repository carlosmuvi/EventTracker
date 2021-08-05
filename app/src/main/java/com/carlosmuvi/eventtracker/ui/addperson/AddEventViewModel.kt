package com.carlosmuvi.eventtracker.ui.addperson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.MainDestinations
import com.carlosmuvi.eventtracker.data.person.Person
import com.carlosmuvi.eventtracker.data.person.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {

    val state = MutableStateFlow(AddPersonState(error = null))

    fun save(name: String, navigateUp: () -> Unit) {
        viewModelScope.launch {
            kotlin.runCatching { personRepository.save(Person(id = "", name = name)) }
            navigateUp()
        }
    }
}

data class AddPersonState(
    val error: String?
)
