package com.carlosmuvi.eventtracker.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navigateToAddEvent: () -> Unit,
    navigateToYourEvents: () -> Unit
) {
    HomeScreen(
        onAddEventClick = { navigateToAddEvent() },
        onYourEventsClick = { navigateToYourEvents() }
    )
}

@Composable
fun HomeScreen(
    onAddEventClick: () -> Unit,
    onYourEventsClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onYourEventsClick() },
                content = { Text(text = "Your events") }
            )
        }
        FloatingActionButton(
            onClick = { onAddEventClick() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add event")
        }
    }

}