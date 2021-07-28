package com.carlosmuvi.eventtracker

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlosmuvi.eventtracker.ui.home.HomeScreen

/**
 * Destinations used in the [EventTrackingNavGraph].
 */
object MainDestinations {
    const val ADD_EVENT_ROUTE = "add_event"
    const val HOME_ROUTE = "home"
}

@Composable
fun EventTrackingNavGraph(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    appContainer: AppContainer,
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            HomeScreen(
                navigateToAddEvent = actions.navigateToAddEvent,
                navigateToYourEvents = {}
            )
        }
        composable(MainDestinations.ADD_EVENT_ROUTE) {
            AddEventScreen(appContainer.eventRepository)
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateToAddEvent: () -> Unit = {
        navController.navigate(MainDestinations.ADD_EVENT_ROUTE)
    }
}