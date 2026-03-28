package com.example.objectandcomposestudy.helpers

class CarEngine {
    //like static block
    companion object {
        var DEFAULT_ENGINE = "V2 MAX"
        fun getDefaultEngine(): String {
            return DEFAULT_ENGINE
        }
    }


}