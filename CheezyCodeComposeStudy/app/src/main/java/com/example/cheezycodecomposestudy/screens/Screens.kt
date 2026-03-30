package com.example.cheezycodecomposestudy.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheezycodecomposestudy.R


@Preview(heightDp = 500, widthDp = 300)
@Composable
private fun BlogCategoryPreview() {

    // LazyHorizontalGrid() { }
    //LazyRow
    //LazyHorizontalStaggeredGrid


    LazyColumn() {
        //single item
//        item {
//            BlogCategory(R.drawable.barclay, "Gufran Khurhsid", "Toronto")
//        }

        items(getCategoryList()) { category ->
            BlogCategory(category.img, category.title, category.subTitle)
        }

        item {
            CircularProgressIndicator(modifier = Modifier.width(50.dp))
        }
    }


//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        getCategoryList().map { category ->
//            BlogCategory(category.img, category.title, category.subTitle)
//        }
//    }

}


@Composable
fun BlogCategory(img: Int, title: String, subTitle: String, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = modifier
            .padding(8.dp)
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
            //.background(Color.Yellow)
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    // .border(2.dp, Color.Gray, CircleShape)
                    .weight(0.2f)
            )

            ItemDescription(title, subTitle, Modifier.weight(0.8f))
        }
    }
}

@Composable
public fun ItemDescription(
    title: String,
    subTitle: String,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            // fontWeight = FontWeight.Bold
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = subTitle,
            // fontSize = 12.sp,
            // fontWeight = FontWeight.Thin,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

data class Category(val img: Int, val title: String, val subTitle: String)

fun getCategoryList(): MutableList<Category> {
    val list = mutableListOf<Category>()
    list.add(Category(R.drawable.barclay, "Gufran Khurhsid", "Toronto"))
    list.add(Category(R.drawable.hurray, "Salman Khurhsid", "Glassgow"))
    list.add(Category(R.drawable.thankyou, "Farhan Khurhsid", "New Dlhi"))
    list.add(Category(R.drawable.barclay, "Azra Mehwish", "Toronto"))
    list.add(Category(R.drawable.thankyou, "Nasreen Khurshid", "Ghaziabad"))
    list.add(Category(R.drawable.barclay, " Khurhsid ", "Toronto"))
    list.add(Category(R.drawable.hurray, "Rafia", "Glassgow"))
    list.add(Category(R.drawable.thankyou, "Jamia", "New Dlhi"))
    list.add(Category(R.drawable.barclay, "Sulatan", "Toronto"))
    list.add(Category(R.drawable.thankyou, "Sakina  ", "Ghaziabad"))

    list.add(Category(R.drawable.barclay, "Gufran Khurhsid", "Toronto"))
    list.add(Category(R.drawable.hurray, "Salman Khurhsid", "Glassgow"))
    list.add(Category(R.drawable.thankyou, "Farhan Khurhsid", "New Dlhi"))
    list.add(Category(R.drawable.barclay, "Azra Mehwish", "Toronto"))
    list.add(Category(R.drawable.thankyou, "Nasreen Khurshid", "Ghaziabad"))
    list.add(Category(R.drawable.barclay, " Khurhsid ", "Toronto"))
    list.add(Category(R.drawable.hurray, "Rafia", "Glassgow"))
    list.add(Category(R.drawable.thankyou, "Jamia", "New Dlhi"))
    list.add(Category(R.drawable.barclay, "Sulatan", "Toronto"))
    list.add(Category(R.drawable.thankyou, "Sakina  ", "Ghaziabad"))

    return list
}