package com.death.gallery.components

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.death.gallery.R
import com.death.gallery.ui.theme.GalleryUTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageGallery(
    modifier: Modifier = Modifier,
    images: List<Image>
) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val enlargeImageLabel = stringResource(R.string.cd_enlarge_image)

    Box(contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = modifier
        ) {
            items(images) { image ->
                GalleryImage(
                    uri = image.uri,
                    scaleType = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .clickable(onClickLabel = enlargeImageLabel) {
                            selectedImage = image.uri
                        }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedImage != null,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            selectedImage?.let {
                GalleryPreview(selectedImage = it, modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        selectedImage = null
                    })
            }
        }
    }
}


data class Image(val id: Long, val uri: Uri, val name: String)


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_ImageGallery() {
    GalleryUTheme() {
        ImageGallery(
            modifier = Modifier.fillMaxWidth(),
            images = listOf(
                Image(id = 0L, uri = Uri.EMPTY, name = "image"),
                Image(id = 1L, uri = Uri.EMPTY, name = "image")
            )
        )
    }
}


fun retrieveMedia(context: Context): List<Image> {
    val images = mutableListOf<Image>()
    val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)

    context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )?.use { cursor->
        val idColumn = cursor.getColumnIndexOrThrow(projection[0])
        val nameColumn = cursor.getColumnIndexOrThrow(projection[1])

        while(cursor.moveToNext()){
            val id = cursor.getLong(idColumn)
            val name = cursor.getString(nameColumn)

            val contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id)
            images.add(Image(id, contentUri, name))
        }
    }

    return images
}