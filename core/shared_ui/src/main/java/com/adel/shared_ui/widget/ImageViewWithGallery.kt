package com.adel.shared_ui.widget

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adel.shared_ui.R
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ImageViewWithGallery() {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    // State to hold the selected image URI
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

    // Activity Result Launcher for picking images
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == android.app.Activity.RESULT_OK) {
                val uri = result.data?.data
                selectedImageUri.value = uri

            }
        }
    if(selectedImageUri.value!=null)
        imageBitmap =
            MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri.value)
                .asImageBitmap()

    // Function to open the gallery
    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
    Button(onClick = { openGallery()}) {
        
    }
    // Composable content
    imageBitmap?.let {
        Image(
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth()
                .background(Color.Transparent, shape = RoundedCornerShape(24.dp))
                .clickable(onClick = { openGallery() }),
            contentDescription = "Select Image",
            bitmap = imageBitmap!!// Placeholder if no image is selected
        )
    }

}
