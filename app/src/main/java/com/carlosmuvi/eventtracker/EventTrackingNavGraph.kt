package com.carlosmuvi.eventtracker

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carlosmuvi.eventtracker.ui.addevent.AddEventScreen
import com.carlosmuvi.eventtracker.ui.addperson.AddPersonScreen
import com.carlosmuvi.eventtracker.ui.home.HomeScreen
import com.carlosmuvi.eventtracker.ui.login.LoginScreen
import com.carlosmuvi.eventtracker.ui.yourevents.YourEventsScreen

/**
 * Destinations used in the [EventTrackingNavGraph].
 */
enum class MainDestinations(val route: String) {
    LOGIN("login"),
    ADD_EVENT("add_event"),
    YOUR_EVENTS("your_events"),
    ADD_PERSON("add_person"),
    HOME("home")
}

@Composable
fun EventTrackingNavGraph(
    scaffoldState: ScaffoldState,
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
        composable(MainDestinations.HOME.route) {
            HomeScreen(
                navigateToAddEvent = actions.navigateToAddEvent,
                navigateToYourEvents = actions.navigateToYourEvents,
                navigateToAddPerson = actions.navigateToAddPerson
            )
        }
        composable(MainDestinations.YOUR_EVENTS.route) {
            YourEventsScreen()
        }
        composable(MainDestinations.ADD_EVENT.route) {
            AddEventScreen(navigateUp = actions.navigateUp)
        }
        composable(MainDestinations.ADD_PERSON.route) {
            AddPersonScreen(
                navigateUp = actions.navigateUp,
                scaffoldState = scaffoldState
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateUp: () -> Unit = { navController.navigateUp() }
    val navigateToHome: () -> Unit =
        { navController.navigate(MainDestinations.HOME.route) }
    val navigateToAddEvent: () -> Unit =
        { navController.navigate(MainDestinations.ADD_EVENT.route) }
    val navigateToAddPerson: () -> Unit =
        { navController.navigate(MainDestinations.ADD_PERSON.route) }
    val navigateToYourEvents: () -> Unit =
        { navController.navigate(MainDestinations.YOUR_EVENTS.route) }
}