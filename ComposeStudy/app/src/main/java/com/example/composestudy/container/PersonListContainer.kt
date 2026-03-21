package com.example.composestudy.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestudy.model.Person
import com.example.composestudy.view.PersonViewItem
import kotlinx.coroutines.delay

@Composable
fun PersonListContainer(personList: List<Person>) {

    val listState = rememberLazyListState()

    ///make sure compose detects the change if person item is changed
    // val personList by remember { mutableListOf<Person>() }


    LaunchedEffect(Unit) {
        delay(5000)
        listState.animateScrollToItem(5)
    }

    LazyColumn(
        state = listState,
        //modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        ///items(items) { it ->  } without index
        itemsIndexed(
            items = personList,
            key = { index, person -> person.id })
        {   index, person ->
            PersonViewItem(person)
        }
    }

}

@Preview
@Composable
private fun PersonListContainer() {
    PersonListContainer(
        listOf(
            Person(
                id = 3,
                firstName = "Charlie",
                lastName = "Williams",
                age = 35
            ),
            Person(id = 4, firstName = "David", lastName = "Brown", age = 40),
        )
    )
}
