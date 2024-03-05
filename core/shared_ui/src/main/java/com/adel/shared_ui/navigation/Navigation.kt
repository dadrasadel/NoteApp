package com.adel.shared_ui.navigation

sealed class NavigationScreen(val route: String) {

    sealed class BottomSheet {

        object WorkScheduleSheet : NavigationScreen("work_schedule_sheet")
    }

    sealed class Screen {

        object Note : NavigationScreen("note_screen")

        object Audio : NavigationScreen("audio_screen")

        object Message : NavigationScreen("message_screen")

        object Settings : NavigationScreen("settings_screen")

        object Work : NavigationScreen("work_screen")


    }
}