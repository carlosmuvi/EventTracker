package com.carlosmuvi.eventtracker.ui.yourevents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carlosmuvi.eventtracker.data.Event
import com.carlosmuvi.eventtracker.ui.addevent.AddEventViewModel
import com.carlosmuvi.eventtracker.ui.components.DropdownMenu
import kotlinx.coroutines.launch

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
        TopAppBar(title = { Text(text = "Add event") })
        Box(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                events.forEach {
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun YourEventsScreenPreview() {
    YourEventsScreen(emptyList())
}