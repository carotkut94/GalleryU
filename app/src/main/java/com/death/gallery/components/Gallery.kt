package com.death.gallery.components

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Gallery() {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val permissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    var retrieveMedia by remember {
        mutableStateOf<List<Image>?>(null)
    }

    LaunchedEffect(key1 = permissionState.hasPermission){
        if(permissionState.hasPermission){
            scope.launch(Dispatchers.IO) {
                val retrievedMedia = retrieveMedia(context = context)
                withContext(Dispatchers.Main){
                    retrieveMedia = retrievedMedia
                }
            }
        }
    }

    MaterialTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            permissionState = permissionState,
            media = retrieveMedia,
            openSettings = {
                context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${context.packageName}")))
            }
        )
    }
}
