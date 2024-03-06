package com.adel.shared_ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DynamicTextView(
    initialText: String,
    modifier: Modifier = Modifier,
    txtStyle: TextStyle?=MaterialTheme.typography.titleSmall,
    isEditing:MutableState<Boolean>
) {
    var text by remember { mutableStateOf("") }
    if (isEditing.value) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = {
                isEditing.value = false
            }),
            textStyle = txtStyle!!,
            label = { Text(text = initialText)})

    } else {
        Text(
            text = text,
            style = txtStyle!!,
            modifier = modifier
                .clickable {
                    isEditing.value = true
                }
        )
    }
}
@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}