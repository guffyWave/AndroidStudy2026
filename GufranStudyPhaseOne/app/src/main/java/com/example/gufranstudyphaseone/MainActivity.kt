package com.example.gufranstudyphaseone

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gufranstudyphaseone.ui.theme.GufranStudyPhaseOneTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GufranStudyPhaseOneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        this.getString(R.string.app_name)


        //IntentView
//        val intent = Intent()
//            .setAction(Intent.ACTION_VIEW)
//            .setData(Uri.parse())
//
//        startActivity(intent)





        //rouiT
//        Thread {
//            println("Thread")
//            runOnUiThread {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
//                startActivity(intent)
//            }
//        }.start();


    }

    //sbc
    ///////////////////////////////////////////////////////////////////////////
    // 
    ///////////////////////////////////////////////////////////////////////////


    //newInstance
//    fun newInstance(): {
//        val args = Bundle()
//
//        val fragment = ()
//        fragment.arguments = args
//        return fragment
//    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GufranStudyPhaseOneTheme {
        Greeting("Android")
    }
}