package com.death.gallery.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
@Composable
fun GalleryContent(
    modifier: Modifier = Modifier,
    permissionState: PermissionState,
) {
    LaunchedEffect(key1 = Unit){
        when{
            !permissionState.permissionRequested ->{
                permissionState.launchPermissionRequest()
            }
        }
    }
}