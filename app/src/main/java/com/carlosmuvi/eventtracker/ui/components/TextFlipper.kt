package com.carlosmuvi.eventtracker.ui.components

import androidx.compose.animation.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalAnimationApi
@ExperimentalTime
@Composable
fun TextFlipper(values: List<String>) {
    var index by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = index) {
        delay(Duration.seconds(2))
        index = (index + 1) % values.size
    }

    AnimatedContent(targetState = index) { targetCount ->
        Text(text = values[targetCount], style = MaterialTheme.typography.h3)
    }
}

@ExperimentalAnimationApi
@ExperimentalTime
@Preview
@Composable
fun ViewFlipperPreview() {
    TextFlipper(values = listOf("uno", "dos", "tres"))
}