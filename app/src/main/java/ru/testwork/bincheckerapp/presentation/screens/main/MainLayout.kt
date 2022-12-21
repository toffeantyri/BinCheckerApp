package ru.testwork.bincheckerapp.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.testwork.bincheckerapp.R
import ru.testwork.bincheckerapp.presentation.screens.history.HistoryDetailsView
import ru.testwork.bincheckerapp.presentation.screens.home.HomeView
import ru.testwork.bincheckerapp.presentation.utils.Screen
import ru.testwork.bincheckerapp.presentation.utils.bottomNavigationItems

@Composable
fun MainLayout() {

    val navController = rememberNavController()
    Scaffold(
        backgroundColor = colorResource(R.color.white),
        bottomBar = { MainBottomNavigation(navController) }) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.title, modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.title) {
                HomeView()
            }
            composable(Screen.History.title) {
                HistoryDetailsView()
            }
        }
    }
}

@Composable
fun MainBottomNavigation(navController: NavHostController) {
    BottomNavigation(backgroundColor = colorResource(R.color.white)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(ImageVector.vectorResource(id = item.icon), null) },
                label = { Text(item.route) },
                selected = currentRoute == item.route,
                selectedContentColor = colorResource(id = R.color.purple_500),
                unselectedContentColor = colorResource(id = R.color.black),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    MainLayout()
}