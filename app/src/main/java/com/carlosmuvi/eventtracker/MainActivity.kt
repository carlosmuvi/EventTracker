package com.carlosmuvi.eventtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as EventTrackingApplication).container

        setContent {
            EventTrackingApp(appContainer = appContainer)
        }
    }
}