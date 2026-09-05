package com.example.appclase.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.appclase.model.Event
import com.example.appclase.ui.components.EventItem
import androidx.compose.foundation.ExperimentalFoundationApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EventListScreen(
    events: List<Event>,
    onAddClick: () -> Unit,
    onDeleteEvent: (String) -> Unit
) {
    var isContainerExpanded by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Eventos") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                modifier = Modifier.semantics {
                    contentDescription = "Agregar nuevo evento"
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Tarjeta Plegable Contenedora de Lista de Eventos (Requerimientos 1 y 2)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .semantics {
                        contentDescription = if (isContainerExpanded) {
                            "Sección plegable de eventos, expandida. Contiene ${events.size} eventos."
                        } else {
                            "Sección plegable de eventos, colapsada. Toca para mostrar la lista."
                        }
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Event,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "Lista de Eventos Plegable (${events.size})",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        IconButton(
                            onClick = { isContainerExpanded = !isContainerExpanded },
                            modifier = Modifier.semantics {
                                contentDescription = if (isContainerExpanded) "Colapsar lista de eventos" else "Expandir lista de eventos"
                            }
                        ) {
                            Icon(
                                imageVector = if (isContainerExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                contentDescription = null
                            )
                        }
                    }

                    if (isContainerExpanded) {
                        Spacer(modifier = Modifier.height(8.dp))
                        if (events.isEmpty()) {
                            Text(
                                text = "No hay eventos agregados aún. Usa el botón '+' para crear uno.",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 500.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    items = events,
                                    key = { it.id }
                                ) { event ->
                                    Box(modifier = Modifier.animateItemPlacement()) {
                                        EventItem(
                                            event = event,
                                            onDelete = onDeleteEvent
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
