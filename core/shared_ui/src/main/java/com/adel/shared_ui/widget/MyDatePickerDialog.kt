package com.adel.shared_ui.widget

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePickerDialog(
    onDismiss:()->Unit,
    onDateSelected: (Long) -> Unit,
    initialDate:Long
){
    val selectDateState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate,
        initialDisplayMode = DisplayMode.Picker)
    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectDateState.selectedDateMillis!!)
                onDismiss()
            }) {
            Text(text = "ok")
        }},
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "Cancel")
            }
        }
        ) {
        DatePicker(state = selectDateState)

    }
}