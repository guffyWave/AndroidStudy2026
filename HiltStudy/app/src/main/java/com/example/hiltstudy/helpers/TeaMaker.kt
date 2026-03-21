package com.example.hiltstudy.helpers

import android.util.Log
import javax.inject.Inject

const val TAG = "TeaMaker"

class TeaMaker @Inject constructor(val steamConverter: SteamConverter) {

    fun getSteam() {
        steamConverter.makeSteam()
    }

    fun makeTea() {
        Log.d(TAG, "makeTea: -- ")
    }
}