package com.adel.shared_ui.navigation

import androidx.annotation.StringRes
import com.adel.shared_ui.R

sealed class NavigationModel (
    val route: String,
    @StringRes
    val title: Int,
    val icon: Int
    ) {
        object Note : NavigationModel(
            route = NavigationScreen.Screen.Note.route,
            title = R.string.note,
            icon = R.drawable.ic_note
        )

        object Audio : NavigationModel(
            route = NavigationScreen.Screen.Audio.route,
            title = R.string.audio,
            icon = R.drawable.ic_microphone
        )

        object Message : NavigationModel(
            route = NavigationScreen.Screen.Message.route,
            title = R.string.message,
            icon = R.drawable.ic_notification
        )

        object Settings : NavigationModel(
            route = NavigationScreen.Screen.Settings.route,
            title = R.string.settings,
            icon = R.drawable.ic_setting
        )
    }