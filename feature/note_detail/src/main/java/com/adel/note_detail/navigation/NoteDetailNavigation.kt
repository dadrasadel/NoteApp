package com.adel.note_detail.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adel.shared_ui.navigation.NavigationScreen
import com.adel.note_detail.NoteDetailScreen
import com.adel.note_detail.notification.getNotificationPermission


fun NavGraphBuilder.noteDetail(
    navController: NavController,
    bottomNavSate: MutableState<Boolean>
){
    composable(route= "${NavigationScreen.Screen.NoteDetail.route}?noteType={noteType}",
        arguments = listOf(
            navArgument( "noteType"){
                type= NavType.StringType
                nullable=true
            },
            /*navArgument("userEntity"){
                nullable=true
                type= NavType.StringType
            }*/
        )
    ){ backStackEntry->
        val arguments= backStackEntry.arguments
        bottomNavSate.value=false
        NoteDetailScreen(navController,arguments)
    }
}