package com.death.gallery.components

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Gallery(){

    val permissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    MaterialTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            permissionState = permissionState
        )
    }
}