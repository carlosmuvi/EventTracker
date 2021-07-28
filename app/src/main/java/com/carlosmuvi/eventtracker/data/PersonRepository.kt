package com.carlosmuvi.eventtracker.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface PersonRepository {
    suspend fun save(person: Person)
    fun observe(): Flow<List<Person>>
}

class PersonRepositoryImpl : PersonRepository {

    private val persons = MutableStateFlow<List<Person>>(emptyList())

    override suspend fun save(person: Person) {
        persons.emit(persons.value + person)
    }

    override fun observe(): Flow<List<Person>> {
        return persons
    }
}