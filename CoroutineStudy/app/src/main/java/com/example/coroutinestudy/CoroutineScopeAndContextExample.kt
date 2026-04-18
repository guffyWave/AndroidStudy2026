package com.example.coroutinestudy

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

const val TAG = "ScopeAndContextExample"

//context - "what" (the configuration)
//creating context
//context= job+dispatcher+name+excpetionhandler
val contextSong = Dispatchers.IO + CoroutineName("Songs Downloader")

val contextDownloadManager =
    Dispatchers.Main + CoroutineName("Download Manager") + Job() + CoroutineExceptionHandler { _, throwable ->
        Log.d(TAG, "Download Exception: ${throwable.message}")
    }

//Scope is the "where/how long" (the boundary) the corountines will run
val scopeForSongs = CoroutineScope(contextSong)

val downloadManagerScope = CoroutineScope(contextDownloadManager)
