package com.adel.work

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.adel.work.notification.NoteReminderWorker
import com.adel.work.notification.getNotificationPermission
import java.util.concurrent.TimeUnit

@Composable
fun WorkScreen(
    navController: NavController
) {
    workScreenImpl(navController = navController)
}

@Composable
fun workScreenImpl(navController: NavController?) {
    val context = LocalContext.current
    var reminderTime=System.currentTimeMillis()+1000
    Scaffold(
        Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    IconButton(onClick = { navController?.popBackStack() }, modifier = Modifier.padding(start = 16.dp)) {
                        Icon(
                            painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_back),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_rington),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        val reminderWorkRequest = OneTimeWorkRequestBuilder<NoteReminderWorker>()
                            .setInitialDelay(10000, TimeUnit.MILLISECONDS)
                            .setInputData(workDataOf("title" to "adel title"))
                            .setInputData(workDataOf("description" to "adel desc"))
                            .build()

                        WorkManager.getInstance().enqueue(reminderWorkRequest)
                    }, modifier = Modifier.padding(end = 16.dp)) {
                        Icon(
                            painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_download_circle),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    }
                }
            }
        }) { padding ->
        getNotificationPermission()
        var isEditing = remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Row(Modifier.padding(vertical = 16.dp)) {
                    Row(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(32.dp),
                                color = Color.White
                            )
                            .padding(8.dp), verticalAlignment = Alignment.CenterVertically


                    ) {
                        Icon(
                            painter =
                            painterResource(id = com.adel.shared_ui.R.drawable.ic_tunder),
                            contentDescription = null,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    shape = CircleShape
                                )
                        )
                        Text(
                            text = context.getString(com.adel.shared_ui.R.string.work),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_avatar_group),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(56.dp)
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                        )
                        .padding(start = 16.dp, bottom = 80.dp, end = 16.dp)
                ) {
                    item {
                        Text(
                            text = "Heli Website Design",
                            modifier = Modifier.padding(top = 32.dp, start = 8.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        var textValue by remember { mutableStateOf("Click me to edit") }
                        ConvertibleEditText(
                            isEditing = isEditing,
                            initialText = textValue,
                            onTextChange = { textValue = it }
                        )

                    }
                }

            }
        }
        if (isEditing.value)
            BottomEditOption(isEditing)


    }


}


@Preview()
@Composable
fun ShowWorkScreen() {
    workScreenImpl(null)
}

@Composable
fun calculateGridHeight(): Dp {
    val screenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }
    val itemWidth = (screenWidth / 2).dp
    return itemWidth * 10
}

@Composable
fun BottomEditOption(isEditing: MutableState<Boolean>) {
    var selectedTab by remember {
        mutableIntStateOf(0)
    }
    val tabs = arrayListOf<Int>(
        com.adel.shared_ui.R.drawable.ic_link,
        com.adel.shared_ui.R.drawable.ic_smallcaps,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = RoundedCornerShape(32.dp)
                )
                .fillMaxWidth(0.8f)
        ) { // Apply padding to the Row
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab])
                            .height(4.dp)
                            .padding(horizontal = 20.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    )
                },
                divider = {},
                modifier = Modifier.fillMaxWidth(0.7f),
                tabs = {
                    tabs.forEachIndexed { index, value ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = {
                                Icon(
                                    painter = painterResource(id = value),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            })
                    }
                })

            Spacer(modifier = Modifier.weight(0.1f))
            IconButton(onClick = { isEditing.value = false }) {
                Icon(
                    painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_check),
                    tint = Color.Unspecified,
                    modifier = Modifier.windowInsetsPadding(WindowInsets(right = 4.dp)),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ConvertibleEditText(
    isEditing: MutableState<Boolean>,
    initialText: String,
    onTextChange: (String) -> Unit
) {

    var textState by remember { mutableStateOf(TextFieldValue(initialText)) }
    Column {
        if (isEditing.value) {
            BasicTextField(
                value = textState,
                onValueChange = {
                    textState = it
                    onTextChange(it.text)
                },
                modifier = Modifier
                    .clickable { isEditing.value = true }
                    .padding(8.dp)
            )
        } else {
            Text(
                text = textState.text,
                modifier = Modifier
                    .clickable { isEditing.value = true }
                    .padding(8.dp)
            )
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(end = 16.dp)
                .heightIn(max = calculateGridHeight()),
            content = {
                items(1) {
                    Image(
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.sample_photo_1),
                        contentDescription = null,
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(start = 16.dp, top = 16.dp)
                    )
                }

            })
    }


}
