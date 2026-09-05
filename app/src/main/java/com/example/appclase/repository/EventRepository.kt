package com.example.appclase.repository

import com.example.appclase.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor() {

    private val _events = MutableStateFlow<List<Event>>(
        listOf(
            Event("1", "Taller de Jetpack Compose", "Aprende interfaz declarativa y animaciones", "2026-09-10"),
            Event("2", "Conferencia Hilt & DI", "Arquitectura limpia e inyección de dependencias", "2026-09-15")
        )
    )
    val events: StateFlow<List<Event>> = _events

    fun addEvent(title: String, description: String, date: String) {
        val newEvent = Event(
            id = System.currentTimeMillis().toString(),
            title = title,
            description = description,
            date = date,
            isAdded = true
        )
        _events.value = _events.value + newEvent
    }

    fun deleteEvent(eventId: String) {
        _events.value = _events.value.filterNot { it.id == eventId }
    }
}
