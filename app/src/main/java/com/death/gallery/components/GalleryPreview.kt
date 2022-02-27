package com.death.gallery.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

@Composable
fun GalleryPreview(modifier: Modifier = Modifier, selectedImage: Uri) {
    Box(modifier = modifier.background(Color.Black), contentAlignment = Alignment.Center) {
        GalleryImage(
            uri = selectedImage,
            scaleType = ContentScale.None,
            modifier = Modifier.fillMaxWidth()
        )
    }
}