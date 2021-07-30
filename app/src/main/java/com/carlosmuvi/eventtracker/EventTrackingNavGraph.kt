package com.carlosmuvi.eventtracker

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlosmuvi.eventtracker.ui.addevent.AddEventScreen
import com.carlosmuvi.eventtracker.ui.home.HomeScreen
import com.carlosmuvi.eventtracker.ui.yourevents.YourEventsScreen

/**
 * Destinations used in the [EventTrackingNavGraph].
 */
object MainDestinations {
    const val ADD_EVENT_ROUTE = "add_event"
    const val YOUR_EVENTS = "your_events"
    const val HOME_ROUTE = "home"
}

@Composable
fun EventTrackingNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            HomeScreen(
                navigateToAddEvent = actions.navigateToAddEvent,
                navigateToYourEvents = actions.navigateToYourEvents
            )
        }
        composable(MainDestinations.YOUR_EVENTS) {
            YourEventsScreen()
        }
        composable(MainDestinations.ADD_EVENT_ROUTE) {
            AddEventScreen(navigateUp = actions.navigateUp)
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateUp: () -> Unit = { navController.navigateUp() }
    val navigateToAddEvent: () -> Unit = { navController.navigate(MainDestinations.ADD_EVENT_ROUTE) }
    val navigateToYourEvents: () -> Unit = { navController.navigate(MainDestinations.YOUR_EVENTS) }
}