package com.carlosmuvi.eventtracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlosmuvi.eventtracker.ui.addevent.AddEventScreen
import com.carlosmuvi.eventtracker.ui.home.HomeScreen
import com.carlosmuvi.eventtracker.ui.login.LoginScreen
import com.carlosmuvi.eventtracker.ui.yourevents.YourEventsScreen

/**
 * Destinations used in the [EventTrackingNavGraph].
 */
enum class MainDestinations(val route: String) {
    LOGIN("login"),
    ADD_EVENT_ROUTE("add_event"),
    YOUR_EVENTS("your_events"),
    HOME_ROUTE("home")
}

@Composable
fun EventTrackingNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: MainDestinations
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(MainDestinations.LOGIN.route) {
            LoginScreen(navigateToHome = actions.navigateToHome)
        }
        composable(MainDestinations.HOME_ROUTE.route) {
            HomeScreen(
                navigateToAddEvent = actions.navigateToAddEvent,
                navigateToYourEvents = actions.navigateToYourEvents
            )
        }
        composable(MainDestinations.YOUR_EVENTS.route) {
            YourEventsScreen()
        }
        composable(MainDestinations.ADD_EVENT_ROUTE.route) {
            AddEventScreen(navigateUp = actions.navigateUp)
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateUp: () -> Unit = { navController.navigateUp() }
    val navigateToHome: () -> Unit =
        { navController.navigate(MainDestinations.HOME_ROUTE.route) }
    val navigateToAddEvent: () -> Unit =
        { navController.navigate(MainDestinations.ADD_EVENT_ROUTE.route) }
    val navigateToYourEvents: () -> Unit =
        { navController.navigate(MainDestinations.YOUR_EVENTS.route) }
}