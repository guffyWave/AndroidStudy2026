package com.example.composestudy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import coil3.transform.RoundedCornersTransformation
//import coil3.compose.AsyncImage
import com.example.composestudy.ui.theme.ComposeStudyTheme

@Composable
fun CoilImageStudy(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            contentAlignment = Alignment.Center

        ) {
            // val painter = remem

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://d2zp5xs5cp8zlg.cloudfront.net/image-84984-800.jpg")
                    .crossfade(true)
                    //  .placeholder(R.drawable.placeholder_img)
                    //  .erro(R.drawable.error_img)
                    .transformations(
                        //  BlurTransformation(context = LocalContext.current, radius = 10f),
                        // GrayscaleTransformation(),
                        CircleCropTransformation(),
//                        RoundedCornersTransformation(radius = 16f) // 16px, not dp
                    )
                    .build(),
                contentDescription = "Image",
                modifier = Modifier
                    .size(128.dp)
                    .clip(RoundedCornerShape(16.dp)), // Additional rounding in Compose
            )
        }
    }
}

@Preview
@Composable
private fun CoilImageStudyPreview() {
    ComposeStudyTheme {
        CoilImageStudy()
    }
}



