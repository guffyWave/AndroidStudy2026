package com.example.hiltstudy.management

import androidx.appcompat.app.AlertDialog
import javax.inject.Inject

class DialogHelper @Inject constructor(val dialogBuilder: AlertDialog.Builder) {

    fun displayDialog(): Unit {
        dialogBuilder.setTitle("Gufran Dialog").setMessage("This is a dialog").show()
    }
}