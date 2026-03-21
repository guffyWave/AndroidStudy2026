package com.example.composestudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


class ComposableCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun InputNumberPanel(modifier: Modifier = Modifier) {

}


@Composable
@Preview
private fun InputNumberPanelPreview() {

}