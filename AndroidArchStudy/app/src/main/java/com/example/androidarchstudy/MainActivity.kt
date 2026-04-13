package com.example.androidarchstudy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchstudy.management.MainActivityLifeCycleObserver
import com.example.androidarchstudy.viewmodels.MainViewModel
import com.example.androidarchstudy.viewmodels.MainViewModelFactory

class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel

    private val textView: TextView by lazy {
        findViewById(R.id.textView)
    }

    private val buttonView: Button by lazy {
        findViewById(R.id.buttonView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        lifecycle.addObserver(MainActivityLifeCycleObserver())

        // mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)

        // This links your activity_main.xml file
        setContentView(R.layout.activity_main)

        setText()

        buttonView.setOnClickListener {
            mainViewModel.increment()
            setText()
        }
    }

    fun setText() {
        textView.text = "Value is " + mainViewModel.count
    }


}


