package com.adel.setting.navigation

import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adel.setting.SettingsScreen
import com.adel.shared_ui.navigation.NavigationScreen

fun NavGraphBuilder.settings(
    navController: NavController,
    bottomNavSate:MutableState<Boolean>

    ){
    composable(route = NavigationScreen.Screen.Settings.route){
        bottomNavSate.value=true
        SettingsScreen()

    }
}