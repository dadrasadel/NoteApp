package com.adel.shared_ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adel.data.model.note.NoteType
import com.adel.shared_ui.R
import com.adel.shared_ui.widget.AppBottomSheet

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>
) {
    var showSheet by remember {
        mutableStateOf(false)
    }
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        CompositionLocalProvider(
            LocalLayoutDirection provides
                    if (LocalConfiguration.current.layoutDirection == LayoutDirection.Rtl.ordinal) {
                        LayoutDirection.Rtl
                    } else {
                        LayoutDirection.Ltr
                    }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .weight(1F)
                        .height(56.dp)
                        .padding(end = 16.dp)

                ) {
                    screens.forEach { screen ->
                        AddItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }
                }
                FloatingActionButton(
                    onClick = {
                        showSheet = true
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .width(88.dp)
                        .height(56.dp)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        modifier = Modifier.height(32.dp)

                    )

                }
                if (showSheet) {
                val list = listOf(
                    BottomSheetItem("Work", R.drawable.ic_work),
                    BottomSheetItem("Life Style", R.drawable.ic_lifestyle)
                )
                AppBottomSheet({
                    LazyColumn(content =
                    {
                        items(list.size) { index ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth().clickable {
                                    if (index == 0)
                                        navController.navigate(
                                            "${NavigationScreen.Screen.NoteDetail.route}?noteType=${NoteType.Work.noteName}&"
                                        )
                                    else
                                        navController.navigate(
                                            "${NavigationScreen.Screen.NoteDetail.route}?noteType=${NoteType.LifeStyle.noteName}&"
                                        )
                                    showSheet = false
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = list[index].icon),
                                    contentDescription = null,
                                    modifier = Modifier.padding(16.dp)
                                )
                                Text(
                                    text = list[index].title,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                            Divider(
                                thickness = 0.5.dp,
                                modifier = Modifier.padding(horizontal = 32.dp)
                            )

                        }

                    })
                }, { showSheet = false }
                )
                }
            }

        }
    }
}

data class BottomSheetItem(val title: String, val icon: Int)

@Composable
fun RowScope.AddItem(
    screen: NavigationModel,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = stringResource(id = screen.title),
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        icon = {
            val colorIcon =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    MaterialTheme.colorScheme.primary
                } else
                    Color.Gray
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = null,
                tint = colorIcon, modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        unselectedContentColor = Color.Gray,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        onClick = {
            if (currentDestination?.hierarchy?.any { it.route == screen.route } == false) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {}
                    launchSingleTop = true
                }
            }
        },
    )
}

val screens = listOf(
    NavigationModel.Note,
    NavigationModel.Audio,
    NavigationModel.Message,
    NavigationModel.Settings
)