package com.example.androidarchstudy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchstudy.viewmodels.DinoViewModel

class DinousaurActivity : AppCompatActivity() {

    lateinit var dinoViewModel: DinoViewModel

    private val textView: TextView by lazy {
        findViewById(R.id.textView)
    }

    private val buttonView: Button by lazy {
        findViewById(R.id.buttonView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dinousaur)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dinoViewModel = ViewModelProvider(this).get(DinoViewModel::class.java)

        dinoViewModel.dinoName.observe(this, {
            textView.text = it
        })

        buttonView.setOnClickListener {
            dinoViewModel.updateDinoName("Rajasauras")
        }

    }
}