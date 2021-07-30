package com.carlosmuvi.eventtracker

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.carlosmuvi.eventtracker.ui.theme.EventTrackerTheme

@Composable
fun EventTrackingApp(
) {
    EventTrackerTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            EventTrackingNavGraph(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }
}