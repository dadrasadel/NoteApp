package com.adel.note_detail.image

import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun LoadImageFromFile(
    filePath: String,
    bitmapState: MutableState<ImageBitmap?>
) {
    LaunchedEffect(filePath) {
        try {
            bitmapState.value = loadImageFromFile(filePath)
        } catch (ex: Exception) {
            Log.d("error loading image", ex.message.toString())
        }

    }


}

suspend fun loadImageFromFile(filePath: String): ImageBitmap {
    return withContext(Dispatchers.IO) {
        val file = File(filePath)
        val inputStream = file.inputStream()
        BitmapFactory.decodeStream(inputStream).asImageBitmap()
    }
}

fun getRealImagePath(context: Context, uri: Uri): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    var cursor: Cursor? = null
    return try {
        cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor == null) return null
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        cursor.getString(columnIndex)
    } finally {
        cursor?.close()
    }
}

