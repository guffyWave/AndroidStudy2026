package com.example.cheezycodecomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CircularImage()
        }
    }
}


@Preview()
@Composable
fun CircularImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.hurray),
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape),
        contentDescription = ""
    )
}

//@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun SimpleModifierStudy() {
    Text(
        text = "This is long text",
        modifier = Modifier
            .background(Color.Gray)
            .clickable {
            }
            .size(50.dp)
            .padding(16.dp)
            .border(1.dp, Color.Blue)
            .clip(CircleShape)
            .background(Color.Yellow)
//            .clickable{
//
//            }
    )

}


//@Preview(showBackground = false, widthDp = 300, heightDp = 500)
@Composable
private fun ListViewItemPreview() {
    Column() {
        ListViewItem(
            R.drawable.barclay, "Gufran Khurshid", "Principal Software Engg. Toronto ",
            Modifier.background(Color.Yellow)
        )
        ListViewItem(
            R.drawable.hurray, "Salman Khurshid", "Chief Engineer . Toronto ", Modifier
                .border(
                    2.dp,
                    Color.Blue
                )
                .blur(5.dp)

        )
        ListViewItem(R.drawable.thankyou, "Farhan Khurshid", "Principal Software Engg. Toronto ")
    }
}


@Composable
fun ListViewItem(imageId: Int, name: String, occupation: String, modifier: Modifier = Modifier) {

    Row(modifier = modifier.padding(8.dp)) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            modifier = Modifier.size(70.dp)
        )
        Column() {
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = occupation,
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

//@Preview(showBackground = false, widthDp = 300, heightDp = 500)
@Composable
fun SimpleBox() {
    Box(contentAlignment = Alignment.BottomCenter) {
        Image(painter = painterResource(R.drawable.thankyou), contentDescription = "")
        Image(painter = painterResource(R.drawable.hurray), contentDescription = "")
    }
}

//@Preview(showBackground = false, widthDp = 300, heightDp = 500)
@Composable
fun SimpleRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "A", fontSize = 24.sp)
        Text(text = "B", fontSize = 24.sp)
    }
}


//@Preview
@Composable
fun SimpleTextField() {
    val state = remember { mutableStateOf("") }

    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        label = {
            Text(text = "Enter your name")
        },
        placeholder = {
            Text(text = "This is place holder ")
        })
}

//@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun SimpleButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red,
            containerColor = Color.Yellow
        ),
        border = BorderStroke(1.dp, Color.Blue),
        enabled = false,
        elevation = ButtonDefaults.buttonElevation(5.dp)
    ) {
        Text(text = "Hello")
        Image(painter = painterResource(R.drawable.hurray), contentDescription = "")
    }

}


//@Preview(showBackground = true, widthDp = 100, heightDp = 500)
@Composable
fun SimpleImage() {
    Image(
        painter = painterResource(R.drawable.barclay),
        contentDescription = "",
        //colorFilter = ColorFilter.tint(Color.Red)
        contentScale = ContentScale.Fit
    )
}


//@Preview(widthDp = 300)
@Composable
fun SimpleGreeting() {
    Text(
        text = "Hello Gufran",
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraLight,
        color = Color.Red,
        fontSize = 36.sp,
        textAlign = TextAlign.Right
    )
}

