package com.example.cheezycodecomposestudy.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class UserViewModel : ViewModel() {
    val selectedUserId = mutableStateOf<String>("")
    fun fetchUserDetails(userId: String) {
        //...
    }

    fun refreshLiveScore() {
        Log.d("GUfran", "refreshing ..... LiveScore: ---->> ")
    }
}


@Composable
fun UserScreen(viewModel: UserViewModel) {
    val userId = viewModel.selectedUserId

    val context = LocalContext.current

    //Runs whenever userId changes.
    //✔ Cancels previous job if the key changes.
    //✔ Perfect for API calls.
    LaunchedEffect(userId) {
        viewModel.fetchUserDetails(userId.value)   // suspend function
    }

    //show one time event , like snackbar,dialog,toast etc
    LaunchedEffect(Unit) {
        Toast.makeText(context, "Bismillah Toast ", Toast.LENGTH_SHORT).show()
    }


    LaunchedEffect(Unit) {
        while (true) {
            viewModel.refreshLiveScore()
            delay(3000)
        }
    }

    Text(text = "This Simple Composable demonstarting LaunchedEffect")
}


@Preview
@Composable
private fun LaunchedEffectPreview() {
    UserScreen(UserViewModel())
}



