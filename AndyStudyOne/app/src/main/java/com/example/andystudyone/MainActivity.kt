package com.example.andystudyone

import android.R.style
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.andystudyone.model.Recipe
import com.example.andystudyone.ui.theme.AndyStudyOneTheme
import com.example.andystudyone.viewmodel.RecipeViewModel
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {

    private val viewModel: RecipeViewModel by viewModels()
    //private val viewModel: RecipeViewModel by viewModels<RecipeViewModel>();
    //lateinit var adapter: RecipeAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            MaterialTheme { RecipeScreen(viewModel) }
//        }

        setContent {
            ButtonTest()
        }

//        setContentView(R.layout.activity_main)
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
//        adapter = RecipeAdapter(emptyList());
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter;
//        viewModel.recipes.observe(this) { adapter.updateRecipes(it) }
//        viewModel.loading.observe(this) { isLoading ->
//            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//        viewModel.error.observe(this) {
//            Log.e("Gufran", "onCreate: " + it)
//            Toast.makeText(this, "Somethig went worng" + it, Toast.LENGTH_SHORT).show()
//        }

    }

}


@Composable
fun ButtonTest(modifier: Modifier = Modifier) {

    var counterState by remember { mutableIntStateOf(100) }
   // counterState.value = counterState.value + 1

    Column(
        modifier = Modifier
            .fillMaxWidth() // Make the Column take full available width
            //  .height(300.dp) // Maintain the height you wanted
            .padding(16.dp), // Add some padding around the column for better visualization
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            counterState = counterState + 1
            Log.d("CLICKED", "CreateBizCard: Clicked  ")
        }) {
            Text(
                text = "Click Me " + counterState,
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center, // Center-align the text
                    fontStyle = FontStyle.Italic // Apply italic text decoration
                ),
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopEnd)
                    .background(Color.Blue) // Set background color to blue
                    .padding(4.dp) // Apply 4dp padding on all sides
                    .padding(
                        start = 10.dp,
                        end = 16.dp
                    ) // Apply margin-like spacing (left 10dp, right 16dp)
            )
        }
    }

}


@Composable
fun HappyScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Text(
            text = "Apple is red",
            style = TextStyle(
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center, // Center-align the text
                fontStyle = FontStyle.Italic // Apply italic text decoration
            ),
            modifier = Modifier
                .wrapContentSize(align = Alignment.TopEnd)
                .background(Color.Blue) // Set background color to blue
                .padding(4.dp) // Apply 4dp padding on all sides
                .padding(
                    start = 10.dp,
                    end = 16.dp
                ) // Apply margin-like spacing (left 10dp, right 16dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null, // decorative element,
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun HappyScreenPreview() {
    AndyStudyOneTheme {
        ButtonTest()
    }
}

@Composable
fun RecipeScreen(viewModel: RecipeViewModel) {
    val recipes by viewModel.recipes.observeAsState(emptyList())
    val isLoading by viewModel.loading.observeAsState(false)
    val error by viewModel.error.observeAsState("")

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            //Text(text = "Hello", modifier = Modifier.align(Alignment.Center))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(recipes) { recipe ->
                    //Text(text = recipe, modifier = Modifier.align(Alignment.Center))
                    RecipeCard(recipe)
                }
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            if (error.isNotEmpty()) {
                Text(
                    text = error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        //image
        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 400
                    )
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
            }, update = { imageView ->
                Glide.with(imageView.context).load(recipe.image).centerCrop().into(imageView)
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "" + recipe.instructions[0], style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndyStudyOneTheme {
        Greeting("Android")
    }
}








