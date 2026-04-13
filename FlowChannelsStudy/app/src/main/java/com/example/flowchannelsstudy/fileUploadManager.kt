package com.example.flowchannelsstudy

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

//Upload multiple files one by one even if triggered concurrently.
//Avoids race conditions, ensures sequential processing.
class FileUploadManager {
   private val uploadChannel= Channel<File>(capacity =Channel.UNLIMITED)

    init {
        CoroutineScope(Dispatchers.IO).launch {

            for (file in uploadChannel) {
                    val result=uploadFileToServer(file)
                Log.d(TAG, "FileUploadManager: RESULT -- ${result} ")
            }

            //internally above code is
            // while (true) {
            //    val file = uploadChannel.receiveCatching().getOrNull() ?: break
            //    upload(file)
            //}
        }
    }

    suspend fun enqueueFile(file: File) {
        uploadChannel.send(file)
    }

    private suspend fun uploadFileToServer(file: File):String{
        Log.d(TAG, "FileUploadManager: -- uploading file ${file.name} ")
        //random upto 5 sec delay
        //delay((1..5).random().toLong()) // simulated upload
        delay(5000)
        return   "-- SUCCESS DONE -- ${file.name} --"
    }

}