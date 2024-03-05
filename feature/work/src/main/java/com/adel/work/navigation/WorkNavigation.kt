package com.adel.work.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import com.adel.shared_ui.navigation.NavigationScreen
import com.adel.work.WorkScreen


fun NavGraphBuilder.work(
    navController: NavController,
    bottomNavSate: MutableState<Boolean>
){
    composable(route= NavigationScreen.Screen.Work.route){
        bottomNavSate.value=false
        WorkScreen(navController)
    }
}