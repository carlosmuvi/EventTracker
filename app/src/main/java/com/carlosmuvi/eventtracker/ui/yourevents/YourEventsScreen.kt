package com.carlosmuvi.eventtracker.ui.yourevents

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosmuvi.eventtracker.data.event.Event
import java.time.LocalDateTime

@Composable
fun YourEventsScreen(
    viewModel: YourEventsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    YourEventsScreen(state.value.events)
}

@Composable
fun YourEventsScreen(
    events: List<Event>
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(title = { Text(text = "Your Events") })
        Box(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                events.forEach { YourEvent(event = it) }
            }
        }
    }
}

@Composable
fun YourEvent(event: Event) {
    Row(Modifier.fillMaxWidth()) {
        Icon(Icons.Filled.DateRange, "contentDescription", Modifier.padding(16.dp))
        Column {
            Text(text = event.description, style = MaterialTheme.typography.h6)
            Text(text = event.startTime.toString(), style = MaterialTheme.typography.body1)
            Text(text = event.endTime.toString(), style = MaterialTheme.typography.body1)
        }
    }
}

@Preview
@Composable
fun YourEventsScreenPreview() {
    YourEventsScreen(
        listOf(
            Event(
                id = "",
                description = "Hola que tal",
                startTime = LocalDateTime.now(),
                endTime = LocalDateTime.now()
            )
        )
    )
}