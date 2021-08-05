package com.carlosmuvi.eventtracker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@Composable
fun TimeRangePicker(
    values: Pair<LocalTime, LocalTime> = LocalTime.now() to LocalTime.now(),
    onValuesChanged: (Pair<LocalTime, LocalTime>) -> Unit
) {
    val (start, end) = values
    val dialog = remember { MaterialDialog() }
    var startDateSelected by remember { mutableStateOf(true) }

    dialog.build(buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        timepicker { date ->
            onValuesChanged(if (startDateSelected) date to end else start to date)
        }
    }

    Row(Modifier.fillMaxWidth()) {
        Button(
            onClick = { startDateSelected = true; dialog.show() },
            content = { Text(text = values.first.toString()) },
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.size(8.dp))
        Button(
            onClick = { startDateSelected = false; dialog.show() },
            content = { Text(text = values.second.toString()) },
            modifier = Modifier.weight(1f)
        )
    }
}