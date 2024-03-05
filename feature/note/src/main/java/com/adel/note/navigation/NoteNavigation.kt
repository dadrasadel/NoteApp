package com.adel.note.navigation
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.adel.note.NoteScreen
import com.adel.shared_ui.navigation.NavigationScreen

fun NavGraphBuilder.note(
    navController: NavController,
    bottomBarSate:MutableState<Boolean>
){
    composable(
        route= NavigationScreen.Screen.Note.route,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) }
        ){
        bottomBarSate.value=true
        NoteScreen(navController)
    }


}