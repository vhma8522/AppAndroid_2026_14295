package com.example.appclase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appclase.ui.screens.EventFormScreen
import com.example.appclase.ui.screens.EventListScreen
import com.example.appclase.viewmodel.EventViewModel

@Composable
fun EventsNavHost(
    viewModel: EventViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val events by viewModel.events.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "event_list"
    ) {
        composable("event_list") {
            EventListScreen(
                events = events,
                onAddClick = {
                    navController.navigate("event_form")
                },
                onDeleteEvent = { eventId ->
                    viewModel.deleteEvent(eventId)
                }
            )
        }

        composable("event_form") {
            EventFormScreen(
                onSaveEvent = { title, description, date ->
                    viewModel.addEvent(title, description, date)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
