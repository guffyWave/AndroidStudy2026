package com.example.androidarchstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidarchstudy.management.MainActivityLifeCycleObserver
import com.example.androidarchstudy.ui.theme.AndroidArchStudyTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycle.addObserver(MainActivityLifeCycleObserver())

        setContent {
            AndroidArchStudyTheme {
            }
        }
    }
}
