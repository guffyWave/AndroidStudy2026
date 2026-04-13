package com.example.hiltstudy.helpers

import javax.inject.Inject

class HikingAdventure @Inject constructor(val hikingLeader: HikingLeader) {

    fun doHiking(): String {
        return "Hiking with Hiking Leader :  ${hikingLeader}"
    }
}