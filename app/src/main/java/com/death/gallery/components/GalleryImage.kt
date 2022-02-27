package com.death.gallery.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.death.gallery.R
import com.death.gallery.ui.theme.GalleryUTheme

@Composable
fun GalleryImage(
    modifier: Modifier = Modifier,
    uri: Uri,
    scaleType: ContentScale
) {
    Image(
        painter = rememberImagePainter(data = uri, builder = {
            placeholder(R.drawable.ic_baseline_image_24)
            crossfade(true)
            error(R.drawable.ic_baseline_error_24)
        }),
        contentDescription = null,
        modifier = modifier,
        contentScale = scaleType,
    )
}


@Preview(showBackground = true)
@Composable
fun Preview_GalleryImage() {
    GalleryUTheme() {
        GalleryImage(
            modifier = Modifier.fillMaxSize(),
            uri = Uri.EMPTY,
            scaleType = ContentScale.Crop
        )
    }
}
