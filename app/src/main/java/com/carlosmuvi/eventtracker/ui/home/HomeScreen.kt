package com.carlosmuvi.eventtracker.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    navigateToAddEvent: () -> Unit,
    navigateToYourEvents: () -> Unit,
    navigateToAddPerson: () -> Unit
) {
    HomeScreen(
        viewModel = hiltViewModel(),
        onAddEventClick = { navigateToAddEvent() },
        onAddPersonClick = { navigateToAddPerson() },
        onYourEventsClick = { navigateToYourEvents() }
    )
}

@Composable
private fun HomeScreen(
    viewModel: HomeViewModel,
    onAddEventClick: () -> Unit,
    onAddPersonClick: () -> Unit,
    onYourEventsClick: () -> Unit
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
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onAddPersonClick() },
                content = { Text(text = "Add person") }
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