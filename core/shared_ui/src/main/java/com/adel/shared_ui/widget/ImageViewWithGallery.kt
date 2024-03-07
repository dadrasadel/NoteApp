package com.adel.shared_ui.widget

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext


@Composable
fun ImageViewWithGallery(
    selectedImageBitmap: MutableState<ImageBitmap?>,
    openGallery: MutableState<Boolean>,
    selectedImgUri:MutableState<Uri?>
) {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Activity Result Launcher for picking images
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            openGallery.value = false
            if (result.resultCode == android.app.Activity.RESULT_OK) {
                val uri = result.data?.data
                selectedImageUri = uri
                selectedImgUri.value=uri
            }
        }
    if (selectedImageUri != null)
        selectedImageBitmap.value =
            MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                .asImageBitmap()

    // Function to open the gallery
    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
    if (openGallery.value)
        openGallery()
}


