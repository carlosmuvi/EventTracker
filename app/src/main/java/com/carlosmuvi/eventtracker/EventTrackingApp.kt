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
    appContainer: AppContainer
) {
    EventTrackerTheme {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        // This top level scaffold contains the app drawer, which needs to be accessible
        // from multiple screens. An event to open the drawer is passed down to each
        // screen that needs it.
        val scaffoldState = rememberScaffoldState()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: MainDestinations.ADD_EVENT_ROUTE
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            EventTrackingNavGraph(
                navController = navController,
                scaffoldState = scaffoldState,
                appContainer = appContainer
            )
        }
    }
}