package com.example.composestudy.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestudy.model.Person
import com.example.composestudy.ui.theme.ComposeStudyTheme


@Composable
fun PersonViewItem(person: Person) {
    // if (person == null) return
    Row(
        modifier = Modifier
            //.padding(6.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = person.firstName,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = person.lastName,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            //  modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp),
            text = "${person.age}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun PersonViewItemPreview() {
    ComposeStudyTheme {
        PersonViewItem(Person(id = 3, firstName = "Charlie", lastName = "Williams", age = 35))
    }
}