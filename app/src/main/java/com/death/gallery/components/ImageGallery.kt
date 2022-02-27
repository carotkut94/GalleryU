package com.death.gallery.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.death.gallery.ui.theme.GalleryUTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageGallery(
    modifier: Modifier = Modifier,
    images: List<Image>
) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

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
                        .clickable {
                            selectedImage = image.uri
                        }
                )
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