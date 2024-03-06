package com.adel.compose_modular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.adel.audio.navigation.audio
import com.adel.message.navigation.message
import com.adel.note.navigation.note
import com.adel.setting.navigation.settings
import com.adel.note_detail.navigation.noteDetail


/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */

@Composable
fun AppNavigation(
    navController: NavHostController,
    startScreen: String,
    bottomBarState: MutableState<Boolean>,
    modifier: Modifier
) {

    NavHost(navController = navController,
        startDestination = startScreen,
        builder = {
            audio(navController,bottomBarState)
            note(navController,bottomBarState)
            settings(navController,bottomBarState)
            message(navController,bottomBarState)
            noteDetail(navController,bottomBarState)
        })

}