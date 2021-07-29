package com.carlosmuvi.eventtracker.ui.addevent

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carlosmuvi.eventtracker.ui.components.DropdownMenu
import kotlinx.coroutines.launch

@Composable
fun AddEventScreen(
    viewModel: AddEventViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    AddEventScreen { coroutineScope.launch { viewModel.save(it) } }
}

@Composable
fun AddEventScreen(
    onEventSubmit: (String) -> Unit
) {
    var description by remember { mutableStateOf("") }

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
                DropdownMenu()
                Spacer(Modifier.size(8.dp))
                Button(
                    onClick = { onEventSubmit(description) },
                    content = { Text(text = "Submit") }
                )
            }
        }
    }
}

@Preview
@Composable
fun AddEventScreenPreview() {
    AddEventScreen() {}
}