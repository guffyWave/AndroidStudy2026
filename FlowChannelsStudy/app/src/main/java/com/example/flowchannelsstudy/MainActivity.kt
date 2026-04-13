package com.example.flowchannelsstudy

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

val TAG = "FlowChannelsStudy"

class MainActivity : ComponentActivity() {

    //@Inject
    private lateinit var fileUploadManager: FileUploadManager

    //analysing backpressure
    private lateinit var textView: TextView
    private var handler: BackPressureHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        // BAD --  here userNameList will not be retunred untill all the user are obtained :(
//        CoroutineScope(Dispatchers.Main).launch {
//           val nameList= getUserNames()
//            nameList.forEach { name->
//                Log.d(TAG, "onCreate: --- ${name}")
//            }
//        }

        //Simple channel example
        // doMain()

        //sequential file upload
//        fileUploadManager = FileUploadManager()
//
//        Log.d(TAG, "onCreate: file upload started  ")
//        CoroutineScope(Dispatchers.Main).launch {
//            fileUploadManager.enqueueFile(File("eminem.mp3"))
//            fileUploadManager.enqueueFile(File("onePiece.txt"))
//            fileUploadManager.enqueueFile(File("design.html"))
//            fileUploadManager.enqueueFile(File("assets.excel"))
//        }

//        setContent {
//            Text(text = "Testing ..")
//            UploadButton(fileUploadManager = fileUploadManager)
//        }

        textView = TextView(this).apply {
            textSize = 22f
            text = "Waiting for sensor values..."
        }

        setContentView(textView)

        // For back pressure Initialize background handler
        handler = BackPressureHandler(
            scope = lifecycleScope,
            onProcessed = { value ->
                textView.text = "Processed: $value"
            }
        )

    }


    suspend fun getUserNames(): List<String> {
        val userNameList = mutableListOf<String>()
        userNameList.add(getUser(1))
        userNameList.add(getUser(2))
        userNameList.add(getUser(3))
        ///here userNameList will not be retunred untill all the user are obtained :(
        return userNameList
    }

    suspend fun getUser(id: Int): String {
        // val randontime=((1..5).random()*1000).toLong()
        delay(1000)
        return "User ${id}"
    }

    //For back pressure
    override fun onDestroy() {
        super.onDestroy()
        handler?.stop()
    }
}


@Composable
fun UploadButton(modifier: Modifier = Modifier, fileUploadManager: FileUploadManager) {

    val fileName = remember { mutableStateOf("") }

    Column(
        Modifier.padding(50.dp)
    ) {
        TextField(value = fileName.value, onValueChange = {
            fileName.value = it
        })

        Button(onClick = {
            //ideally viewmodel should have been invoked
            CoroutineScope(Dispatchers.Main).launch {
                fileUploadManager.enqueueFile(File(fileName.value))
            }

        }) {
            Text(text = "Upload File ")
        }
    }

}