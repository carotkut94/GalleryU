package com.death.gallery.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
@Composable
fun GalleryContent(
    modifier: Modifier = Modifier,
    permissionState: PermissionState,
    media: List<Image>? = null,
    openSettings:()->Unit
) {
    when {
        permissionState.hasPermission -> {
            if (media == null) {
                Box(modifier = modifier, contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                ImageGallery(images = media, modifier = modifier)
            }
        }
        !permissionState.permissionRequested -> {
            PermissionExplainer(modifier = Modifier.fillMaxSize()) {
                permissionState.launchPermissionRequest()
            }
        }
        else -> {
            DeniedPermission(modifier = modifier, handleLaunchSettings = {
                openSettings()
            })
        }
    }
}