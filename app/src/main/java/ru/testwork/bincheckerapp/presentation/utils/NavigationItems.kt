package ru.testwork.bincheckerapp.presentation.utils

import ru.testwork.bincheckerapp.R


sealed class Screen(val title: String) {
    object Home : Screen("Главный")
    object History : Screen("История")
}

data class BottomNavigationItem(
    val route: String,
    val icon: Int
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        Screen.Home.title,
        R.drawable.ic_baseline_credit_card_24
    ),
    BottomNavigationItem(
        Screen.History.title,
        R.drawable.ic_baseline_list_24
    )
)