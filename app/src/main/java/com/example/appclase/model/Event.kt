package com.example.appclase.model

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val isAdded: Boolean = false
)
