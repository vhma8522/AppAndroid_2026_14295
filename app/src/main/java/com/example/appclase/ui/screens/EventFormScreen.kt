package com.example.appclase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventFormScreen(
    onSaveEvent: (String, String, String) -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    var isSubmitted by remember { mutableStateOf(false) }
    var titleHasError by remember { mutableStateOf(false) }
    var descriptionHasError by remember { mutableStateOf(false) }

    // Validación usando LaunchedEffect observando los cambios de los campos y del intento de envío (Requerimiento 7)
    LaunchedEffect(title, description, isSubmitted) {
        if (isSubmitted) {
            titleHasError = title.isBlank()
            descriptionHasError = description.isBlank()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Evento") },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.semantics {
                            contentDescription = "Regresar a la lista de eventos"
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Campo Título
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título del Evento") },
                isError = titleHasError,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "Campo de texto para ingresar el título del evento"
                    }
            )
            // Renderizado condicional del error requerido (Requerimiento 7)
            if (title.isEmpty() && isSubmitted) {
                Text(
                    text = "¡Título requerido!",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 2.dp)
                        .semantics { contentDescription = "Error: ¡Título requerido!" }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Descripción
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del Evento") },
                isError = descriptionHasError,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "Campo de texto para ingresar la descripción del evento"
                    }
            )
            if (description.isEmpty() && isSubmitted) {
                Text(
                    text = "¡Descripción requerida!",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 2.dp)
                        .semantics { contentDescription = "Error: ¡Descripción requerida!" }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Fecha
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Fecha (ej. 2026-09-03)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "Campo de texto para ingresar la fecha del evento"
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    isSubmitted = true
                    if (title.isNotBlank() && description.isNotBlank()) {
                        val finalDate = if (date.isBlank()) "2026-09-03" else date
                        onSaveEvent(title, description, finalDate)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "Boton para guardar el evento creado"
                    }
            ) {
                Text("Guardar Evento")
            }
        }
    }
}
