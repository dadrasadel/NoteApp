package com.adel.shared_ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppTimePicker(showDialog:MutableState<Boolean>,
                      timeState:TimePickerState) {
        var selectedHour by remember { mutableIntStateOf(0) }
        var selectedMinute by remember { mutableIntStateOf(0) }


        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(24.dp))
                        .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimePicker(state = timeState)
                    Text(text = "Time is ${timeState.hour} : ${timeState.minute}")
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showDialog.value = false }) {
                            Text(text = "Dismiss")
                        }
                        TextButton(onClick = {
                            showDialog.value = false
                            selectedHour = timeState.hour
                            selectedMinute = timeState.minute
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                }
            }
        }

//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showDialog = true }) {
//                Text(text = "Show Time Picker")
//            }
//            Text(text = "Time is ${timeState.hour} : ${timeState.minute}")
//        }
    }