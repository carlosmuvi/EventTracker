package com.carlosmuvi.eventtracker.ui.addevent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
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
import com.carlosmuvi.eventtracker.data.person.Person
import com.carlosmuvi.eventtracker.ui.components.DropdownMenu
import com.carlosmuvi.eventtracker.ui.components.TimeRangePicker
import java.time.LocalTime

@Composable
fun AddEventScreen(
    viewModel: AddEventViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    AddEventScreen(
        onEventSubmit = { event -> viewModel.save(event, navigateUp) },
        state = state.value
    )
}

@Composable
private fun AddEventScreen(
    onEventSubmit: (AddEventForm) -> Unit,
    state: AddEventState
) {

    var description by remember { mutableStateOf("") }
    var dateRange by remember { mutableStateOf(LocalTime.now() to LocalTime.now()) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(title = { Text(text = "Add event") })
        Box(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                )
                DropdownMenu(
                    options = state.persons.map { it.name }
                )
                Spacer(Modifier.size(8.dp))
                TimeRangePicker(
                    values = dateRange,
                    onValuesChanged = { dateRange = it }
                )
                Spacer(Modifier.size(8.dp))
                Button(
                    onClick = {
                        onEventSubmit(
                            AddEventForm(
                                description = description,
                                dateRange = dateRange
                            )
                        )
                    },
                    content = { Text(text = "Submit") }
                )
            }
        }
    }
}

data class AddEventForm(
    val description: String,
    val dateRange: Pair<LocalTime, LocalTime>
)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(backgroundColor = 0xFFFFFF)
@Composable
fun AddEventScreenPreview() {
    AddEventScreen(
        onEventSubmit = {},
        state = AddEventState(
            persons = listOf(
                Person(id = "1", name = "Carlos")
            )
        )
    )
}