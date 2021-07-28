package com.carlosmuvi.eventtracker.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    navigateToAddEvent: () -> Unit,
    navigateToYourEvents: () -> Unit
) {
    HomeScreen(
        onAddEventClick = { navigateToAddEvent() }
    )
}

@Composable
fun HomeScreen(
    onAddEventClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Hello!")
        }
        FloatingActionButton(
            onClick = { onAddEventClick() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add event")
        }
    }

}