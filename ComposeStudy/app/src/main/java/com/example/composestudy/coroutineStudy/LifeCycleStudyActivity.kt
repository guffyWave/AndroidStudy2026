package com.example.composestudy.coroutineStudy

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.composestudy.R
import com.example.composestudy.databinding.ActivityLifeCycleStudyBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LifeCycleStudyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifeCycleStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLifeCycleStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            var count = 1;
            while (true) {
                delay(1000)
                println("Count : ${count++}")
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                //..
            }
        }
    }

}