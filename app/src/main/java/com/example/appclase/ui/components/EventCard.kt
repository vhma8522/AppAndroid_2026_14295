package com.example.appclase.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
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

@Composable
fun EventCard(
    event: Event,
    modifier: Modifier = Modifier,
    onExpandToggle: ((Boolean) -> Unit)? = null,
    content: (@Composable () -> Unit)? = null
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .animateContentSize()
            .semantics {
                contentDescription = if (isExpanded) {
                    "Tarjeta de evento ${event.title}, desplegada."
                } else {
                    "Tarjeta de evento ${event.title}, colapsada. Toca para desplegar."
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = event.date,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = {
                        isExpanded = !isExpanded
                        onExpandToggle?.invoke(isExpanded)
                    },
                    modifier = Modifier.semantics {
                        contentDescription = if (isExpanded) {
                            "Colapsar detalles de ${event.title}"
                        } else {
                            "Expandir detalles de ${event.title}"
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                if (content != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    content()
                }
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    MaterialTheme {
        EventCard(
            event = Event(
                id = "1",
                title = "Evento de Prueba",
                description = "Esta es una descripción detallada para probar la expansión de la tarjeta plegable.",
                date = "2026-09-03",
                isAdded = true
            )
        )
    }
}
