package com.example.composestudy.flowstudy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun UserDetails(modifier: Modifier = Modifier) {
    val userRepository = remember { UserRepository() }
    val coroutineScope = rememberCoroutineScope()

    // collectAsState is used (rather than collect) as it automatically converts the Flow emissions into a Compose State
    val users by userRepository.fetchUsers().collectAsState(initial = emptyList())

    val itemMessage by userRepository.flowEmitTest().collectAsState(initial = "Initial")


    //created below for paginated study
    //var _users = remember { mutableStateListOf<User>(*users.value.toTypedArray()) }

    //if we want to use collect , then has to be called within a coroutine (like in a LaunchedEffect or ViewModel)
//    var users by remember { mutableStateOf(emptyList<User>()) }
//    LaunchedEffect(Unit) {
//        userRepository.fetchUsers().collect { newList ->
//            users = newList
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        Text(text = "Users List")



        LazyColumn(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(
                //  userRepository.getUsersLegacy()
                users
            ) { index, user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "${user.name}")
                    Text(text = " ${user.age}")
                    Text(text = " ${user.marks}")
                }

            }
        }

        Button(onClick = {
            /// userRepository.addUser("Salman", 12, 24.0f)
            coroutineScope.launch {
                userRepository.fetchUsers(3, 3).collect { newList ->
                    //    _users = mutableStateListOf<User>(*newList.toTypedArray())
                }
            }

        }) {
            Text(text = "Click Me")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Flow Emitted message : ${itemMessage}")

    }
}

@Composable
@Preview
private fun UserDetailsPreview() {
    UserDetails()
}