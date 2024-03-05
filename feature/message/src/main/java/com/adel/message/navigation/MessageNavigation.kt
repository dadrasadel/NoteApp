package com.adel.message.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adel.message.MessageScreen
import com.adel.shared_ui.navigation.NavigationScreen


fun NavGraphBuilder.message(
    navController: NavController,
    bottomBarState: MutableState<Boolean>

){
    composable(route= NavigationScreen.Screen.Message.route){
        bottomBarState.value=true
        MessageScreen()
    }
}