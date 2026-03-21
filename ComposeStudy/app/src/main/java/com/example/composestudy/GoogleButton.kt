package com.example.composestudy

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestudy.ui.theme.ComposeStudyTheme
import com.example.composestudy.ui.theme.Shapes

@Composable
fun GoogleButton(
    text: String = "Signup with Google",
    loadingText: String = "Creating Account..",
    onClicked: () -> Unit
) {

    var isClicked by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
        onClick = {
            isClicked = !isClicked
            onClicked
        },
        shape = Shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .animateContentSize(tween(durationMillis = 300, easing = LinearOutSlowInEasing)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google Icon",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isClicked) loadingText else text,
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (isClicked) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp), strokeWidth = 2.dp,
                    color =
                        MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoogleButtonPreview() {
    ComposeStudyTheme {
        GoogleButton(
            text = "Sign Up with G+",
            onClicked = { Log.d("google Button", "GoogleButtonPreview: ") })
    }
}