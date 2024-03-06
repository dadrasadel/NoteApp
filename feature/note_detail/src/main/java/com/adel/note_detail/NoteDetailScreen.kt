package com.adel.note_detail

import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.adel.note_detail.notification.NoteReminderWorker
import com.adel.note_detail.notification.getNotificationPermission
import com.adel.shared_ui.theme.lineGray
import com.adel.shared_ui.widget.AppBottomSheet
import com.adel.shared_ui.widget.AppDialog
import com.adel.shared_ui.widget.AppTimePicker
import com.adel.shared_ui.widget.DynamicTextView
import com.adel.shared_ui.widget.ImageViewWithGallery
import com.adel.shared_ui.widget.ShowDatePickerDialog
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

@Composable
fun NoteDetailScreen(
    navController: NavController,
    arguments: Bundle?
) {
    NoteDetailScreenImpl(navController = navController, arguments)
}

@Composable
fun NoteDetailScreenImpl(
    navController: NavController?,
    arguments: Bundle?
) {
    val context = LocalContext.current
    var reminderTime = remember {
        mutableLongStateOf(0)
    }
    val dateFormat = SimpleDateFormat("dd MMMM,hh:mm", Locale.getDefault())

    var showBottomSheet = remember {
        mutableStateOf(false)
    }
    val isTitleEditing = remember { mutableStateOf(true) }
    val isDescriptionEditing = remember { mutableStateOf(false) }
    val isEditing = remember { mutableStateOf(false) }
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
                    IconButton(
                        onClick = { navController?.popBackStack() },
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_back),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        showBottomSheet.value = true
                    }) {
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
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            getNotificationPermission()
            TimePickerSheet(showBottomSheet, selectedTime = reminderTime)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                            text = if (arguments?.getString("noteType") == "work")
                                context.getString(com.adel.shared_ui.R.string.work)
                            else
                                context.getString(com.adel.shared_ui.R.string.life_style),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 16.dp))
                    if (reminderTime.value > 0)
                        Text(
                            text = dateFormat.format(reminderTime.value),
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.3f
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(16.dp)
                        )

                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                        )
                        .padding(top = 16.dp, start = 16.dp, bottom = 80.dp, end = 16.dp)
                ) {
                    item {
                        DynamicTextView(
                            modifier = Modifier.padding(16.dp),
                            initialText = "title",
                            txtStyle = MaterialTheme.typography.titleMedium,
                            isEditing = isTitleEditing
                        )
                        if (!isTitleEditing.value) {
                            isDescriptionEditing.value = true
                            DynamicTextView(
                                modifier = Modifier.padding(16.dp),
                                initialText = "description",
                                txtStyle = MaterialTheme.typography.bodyLarge,
                                isEditing = isDescriptionEditing
                            )
                            Spacer(modifier = Modifier.padding(top = 32.dp))
                            BottomEditOption(isDescriptionEditing)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            ImageViewWithGallery()


                        }
                    }
                }

            }
        }
    }


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
    if (isEditing.value)
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


@Composable
fun TimePickerSheet(showBottomSheet: MutableState<Boolean>, selectedTime: MutableState<Long>) {
    val context = LocalContext.current
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val showDialog = remember {
        mutableStateOf(false)
    }
    showDialog(selectedTime, showDialog)
    if (showBottomSheet.value)
        AppBottomSheet({
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .clickable {
                        selectedTime.value = System.currentTimeMillis() + 60 * 60 * 1000
                        showBottomSheet.value = false
                    }) {
                    Icon(
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_clock),
                        contentDescription = null
                    )
                    Divider(
                        modifier = Modifier
                            .height(20.dp)
                            .width(4.dp)
                            .padding(8.dp)
                            .background(Color.Black)
                    )
                    Text(
                        text = context.getString(com.adel.shared_ui.R.string.later_today),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = dateFormat.format(System.currentTimeMillis() + 60 * 60 * 1000),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp
                    )
                }
                Divider(
                    thickness = 0.5.dp,
                    color = lineGray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .clickable {
                        selectedTime.value = System.currentTimeMillis() + 25 * 60 * 60 * 1000
                        showBottomSheet.value = false
                    }) {
                    Icon(
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_clock),
                        contentDescription = null, modifier = Modifier
                    )
                    Divider(
                        modifier = Modifier
                            .height(20.dp)
                            .width(1.dp)
                            .padding(8.dp)
                            .background(Color.Black)
                    )
                    Text(
                        text = context.getString(com.adel.shared_ui.R.string.tomorrow),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = dateFormat.format(System.currentTimeMillis() + 25 * 60 * 60 * 1000),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp
                    )
                }
                Divider(
                    thickness = 0.5.dp,
                    color = lineGray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        showBottomSheet.value = false
                        showDialog.value = true
                    }) {
                    Icon(
                        painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_clock),
                        contentDescription = null,
                    )
                    Divider(
                        modifier = Modifier
                            .height(20.dp)
                            .width(1.dp)
                            .padding(8.dp)
                            .background(Color.Black)
                    )
                    Text(
                        text = context.getString(com.adel.shared_ui.R.string.pick_date),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { showDialog.value = true }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)

                    }
                }
            }
        }, {
            showBottomSheet.value = false
        }, modifier = Modifier.background(Color.White))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDialog(
    selectedTime: MutableState<Long>,
    showDialog: MutableState<Boolean>
) {
    val context = LocalContext.current
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    val calendar = Calendar.getInstance()
    var showTimePicker = remember {
        mutableStateOf(false)
    }
    val dayDateFormat = SimpleDateFormat("EEEE,dd MMMM", Locale.getDefault())
    val timeDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    var dateTxt = remember {
        mutableStateOf(dayDateFormat.format(System.currentTimeMillis()))
    }
    var timeTxt by remember {
        mutableStateOf(timeDateFormat.format(System.currentTimeMillis()))
    }
    if (showDialog.value)
        AppDialog(onDismiss = { showDialog.value = false }) {
            val timeState = rememberTimePickerState(
                initialHour = calendar.get(Calendar.HOUR_OF_DAY),
                initialMinute = calendar.get(Calendar.MINUTE)
            )
            AppTimePicker(showTimePicker, timeState)
            if (!showTimePicker.value) {
                calendar.set(Calendar.HOUR_OF_DAY, timeState.hour)
                calendar.set(Calendar.MINUTE, timeState.minute)
                timeTxt = timeDateFormat.format(calendar.timeInMillis)
            }

            if (showDatePicker)
                ShowDatePickerDialog(
                    onDismiss = { showDatePicker = false },
                    onDateSelected = { date ->
                        val dateCal = Calendar.getInstance().apply {
                            timeInMillis = date
                        }
                        calendar.set(Calendar.YEAR, dateCal.get(Calendar.YEAR))
                        calendar.set(Calendar.MONTH, dateCal.get(Calendar.MONTH))
                        calendar.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH))
                        dateTxt.value = dayDateFormat.format(calendar.timeInMillis)
                    },
                    initialDate = calendar.timeInMillis
                )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Add Reminder",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                TextField(
                    modifier = Modifier
                        .padding(16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        showDatePicker = true
                                    }
                                }
                            }
                        },
                    readOnly = true,
                    shape = RoundedCornerShape(16.dp),
                    value = dateTxt.value,
                    onValueChange = { },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_scroll),
                            contentDescription = null
                        )
                    })
                TextField(
                    modifier = Modifier
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    value = timeTxt,
                    readOnly = true,
                    onValueChange = { timeTxt = it },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = com.adel.shared_ui.R.drawable.ic_scroll),
                            contentDescription = null
                        )
                    },
                    interactionSource = remember { MutableInteractionSource() }
                        .also { interactionSource ->
                            LaunchedEffect(interactionSource) {
                                interactionSource.interactions.collect {
                                    if (it is PressInteraction.Release) {
                                        showTimePicker.value = true
                                    }
                                }
                            }
                        },
                )
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.spacedBy(8.dp, Alignment.End)
                ) {
                    TextButton(
                        onClick = { showDialog.value = false },
                        Modifier.background(color = Color.Transparent)
                    ) {
                        Text(
                            text = "Cancel",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    TextButton(
                        onClick = {
                            calendar.set(Calendar.HOUR_OF_DAY, timeState.hour)
                            calendar.set(Calendar.MINUTE, timeState.minute)
                            if (calendar.timeInMillis - selectedTime.value > 1000) {
                                selectedTime.value = calendar.timeInMillis
                                showDialog.value = false
                            } else
                                Toast.makeText(context, "Time is Not Valid", Toast.LENGTH_SHORT)
                                    .show()
                        },
                        Modifier
                            .padding(end = 16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text(
                            text = "Accept",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

            }

        }


}

