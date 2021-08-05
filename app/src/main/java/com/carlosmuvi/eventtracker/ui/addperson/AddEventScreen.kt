package com.carlosmuvi.eventtracker.ui.addperson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosmuvi.eventtracker.ui.components.DropdownMenu

@Composable
fun AddPersonScreen(
    viewModel: AddPersonViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    navigateUp: () -> Unit
) {

    val state = viewModel.state.collectAsState()

    if (state.value.error != null) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = state.value.error!!
            )
        }
    }

    AddPersonScreen(
        onPersonSubmit = { name -> viewModel.save(name, navigateUp) }
    )
}

@Composable
private fun AddPersonScreen(
    onPersonSubmit: (String) -> Unit
) {

    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(title = { Text(text = "Add person") })
        Box(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Button(
                    onClick = { onPersonSubmit(name) },
                    content = { Text(text = "Submit") }
                )
            }
        }
    }
}

@Preview
@Composable
fun AddPersonScreenPreview() {
    AddPersonScreen(onPersonSubmit = {})
}