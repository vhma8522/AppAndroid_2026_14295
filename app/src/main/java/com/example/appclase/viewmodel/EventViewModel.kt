package com.example.appclase.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appclase.model.Event
import com.example.appclase.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    val events: StateFlow<List<Event>> = repository.events

    fun addEvent(title: String, description: String, date: String) {
        repository.addEvent(title, description, date)
    }

    fun deleteEvent(eventId: String) {
        repository.deleteEvent(eventId)
    }
}
