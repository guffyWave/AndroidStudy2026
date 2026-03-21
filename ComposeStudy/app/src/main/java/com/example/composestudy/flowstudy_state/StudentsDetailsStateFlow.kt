package com.example.composestudy.flowstudy_state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StudentsDetailsViewStateFlow(modifier: Modifier = Modifier) {

    // Remember repository (simulate viewModel/repository lifespan)
    val studentsRepository = remember { StudentRepositoryStateFlow() }

    // Collect state from StateFlow for Compose reactivity
    val students by studentsRepository.studentList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Text(text = "Students List")
        LazyColumn(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(
                students
            ) { index, student ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "${student.name}")
                    Text(text = " ${student.age}")
                    Text(text = " ${student.marks}")
                }

            }
        }

        Button(onClick = {

            studentsRepository.addUser("Salman", 12, 24.0f)

        }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
@Preview
private fun StudentsDetailsViewStateFlowPreview() {
    StudentsDetailsViewStateFlow()
}