package com.adel.compose_modular

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.adel.shared_ui.navigation.BottomBar
import com.adel.shared_ui.navigation.NavigationScreen
import com.adel.shared_ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var keepSplash = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        installSplashScreen().setKeepOnScreenCondition() {
            keepSplash
        }
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    /**
                     * after create main screen make the splash off
                     */

                    MainScreen {
                        keepSplash = false
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), onLauncherFinished: () -> Unit) {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val systemUiController= rememberSystemUiController()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            BottomBar(navController, bottomBarState)
        },
        content = {
            systemUiController.setStatusBarColor(MaterialTheme.colorScheme.surface)
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                AppNavigation(
                    modifier = Modifier.padding(paddingValues = it),
                    navController = navController,
                    startScreen = NavigationScreen.Screen.NoteDetail.route,
                    bottomBarState = bottomBarState
                )
            }

        }
    )
    onLauncherFinished()


}