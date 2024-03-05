package com.adel.audio.navigation
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adel.audio.AudioScreen
import com.adel.shared_ui.navigation.NavigationScreen

fun NavGraphBuilder.audio(
    navController: NavController,
    bottomBarState:MutableState<Boolean>
){
    composable(route = NavigationScreen.Screen.Audio.route){
        bottomBarState.value=true
        AudioScreen()
    }
}