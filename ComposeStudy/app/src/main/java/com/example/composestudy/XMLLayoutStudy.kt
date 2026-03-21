package com.example.composestudy

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class XMLLayoutStudy : AppCompatActivity() {

    lateinit var userNameTextView: TextView
    lateinit var button: Button
    lateinit var userProfileImage: ImageView

    var userAge = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_xmllayout_study)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userNameTextView = findViewById<TextView>(R.id.userName)
        button = findViewById<Button>(R.id.clickMeButton)
        userNameTextView.text = "Bismillah Hirrahman Nirahim "
        userProfileImage = findViewById<ImageView>(R.id.userProfileImage)

        button.setOnClickListener {
            //userNameTextView.text = "Button Clicked

            //will work fine as lifeCycleScope runs by default on main thread
//            lifecycleScope.launch() {
//                increaseUserAge(50)
//            }


            //below will crash
//            lifecycleScope.launch(Dispatchers.IO) {
//                increaseUserAge(60)
//            }

            lifecycleScope.launch(Dispatchers.IO) {
                //starts the work on IO thread
                userAge += 60

                //updates the UI on main thread
                withContext(Dispatchers.Main) {
                    userNameTextView.text = "User Age : " + userAge
                }
            }

        }

        Glide.with(this).load("https://randomuser.me/api/portraits/men/10.jpg")
            .into(userProfileImage)
    }

    fun increaseUserAge(increaseBy: Int): Unit {
        userAge += increaseBy
        userNameTextView.text = "User Age : " + userAge
    }

}