package com.example.coroutinestudy

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.coroutinestudy.ui.theme.CoroutineStudyTheme
import com.example.coroutinestudy.viewmodel.ZooViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    val zooViewModel: ZooViewModel by viewModels()

    private val _downloadManagerScope = downloadManagerScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d(TAG, "onCreate: MainActivity --- ")

        //checkingSongsDownloaderScope()

        Log.d(TAG, "onCreate: next corutine ----->> ")

        //    checkingDownloadManagerScope()

        // threadSwitching()

        //   jobExample()


        //Job = represents coroutine execution and cancellation.
        //Deferred<T> = represents coroutine execution and a future value of type T.
        //await() = suspends until the value is ready, then returns T.
//        val deferred: Deferred<Int> = async {
//            10
//        }
        //  val value: Int = deferred.await()

        //   simpleAsyncAwaitExample()

        // fetchZooAnimals()

        ///fetchZooAnimalsRace()

        // withContextExample()

        lifecycleScope.launch {
            val zooAmphibians = zooViewModel.fetchZooAmphibians()
            Log.d(TAG, "onCreate: zooAnimals $zooAmphibians")
        }

        val zooAnimals = zooViewModel.zooAnimalsState.value
        Log.d(TAG, "onCreate: zooAnimals  $zooAnimals")



        setContent {

        }
    }


    fun withContextExample() {
        lifecycleScope.launch(Dispatchers.Main) {
            Log.d(TAG, "withContextExample: current thread ${Thread.currentThread().name}")

            val result = withContext(Dispatchers.IO) {
                Log.d(TAG, "withContextExample: in withContext ${Thread.currentThread().name}")
                fetchBirds()
            }

            Log.d(TAG, "withContextExample: result $result")
            Log.d(TAG, "withContextExample: current thread ${Thread.currentThread().name}")
        }
    }

    fun checkingSongsDownloaderScope() {
        scopeForSongs.launch {
            Log.d(TAG, "Scope --  ${this}")
            Log.d(TAG, "Context --- ${coroutineContext}")

            val name = coroutineContext[CoroutineName]
            val job = coroutineContext[Job]

            Log.d(TAG, "on create name+ job = Running in: ${name?.name} with Job: $job")
        }
    }

    fun checkingDownloadManagerScope() {

        //setting Dispatcher will override the Dispacther set in context of the downloadManagerScope
        _downloadManagerScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Scope --  ${this}")
            Log.d(TAG, "Context --- ${coroutineContext}")

            val name = coroutineContext[CoroutineName]
            val job = coroutineContext[Job]
            Log.d(TAG, "on create name+ job = Running in: ${name?.name} with Job: $job")

            throw Exception("This is dummy exception")
        }
    }

    fun threadSwitching() {

        //started with _downloadManager default dispatcher which is MAIN
        _downloadManagerScope.launch {
            Log.d(TAG, "threadSwitching:--> launched ")

            //switches to IO for the data fetch, then comes back to default main
            val info = withContext(Dispatchers.IO) {
                delay(3000)
                "Some Info from Download Manager "
            }
            //updateUI (info)
            Log.d(TAG, "threadSwitching: $info")
        }

    }

    fun jobExample() {
        //Job - Job is the lifecycle handle of a coroutine.
        // It lets you cancel a coroutine, wait for completion, and organize parent-child cancellation behavior in a scope.


        val job = _downloadManagerScope.launch {
            repeat(10) {
                delay(1000)
                Log.d("Demo", "Downloading -> $it")
            }
        }

        //either we cancel the job or scope gets canceled (as we cancelled in onDestroy)
        // job.cancel()

    }

    fun moreExample() {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch(CoroutineName("LoadUserProfile")) {
            Log.d("Demo", "Running ${coroutineContext[CoroutineName]}")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _downloadManagerScope.cancel()
    }

    fun simpleAsyncAwaitExample() {
        lifecycleScope.launch() {
            /// deferredA  Deferred<T> is Job + Result which we can get later with await
            val deferredA = async { fetchA() }
            val deferredB = async { fetchB() }

            val resultA = deferredA.await()
            val resultB = deferredB.await()
            val totalResult = resultA + resultB
            Toast.makeText(this@MainActivity, "Result ${totalResult}", Toast.LENGTH_LONG).show()
            Log.d(TAG, "onCreate: async await $totalResult")

            //If deferredA finishes first, its value is returned. If deferredB finishes first, its value is returned instead
            val result = select {
                deferredA.onAwait { it }
                deferredB.onAwait { it }
            }

        }
    }

    fun fetchZooAnimals() {
        lifecycleScope.launch {
            Log.d(TAG, "fetchZooAnimals called ---")
            //fetchAnimals
            val deferredAnimalsFetch = async(Dispatchers.IO) { fetchAnimals() }
            //fetch Birds
            val deferredBirdFetch = async(Dispatchers.Default) { fetchBirds() }

            val animals = deferredAnimalsFetch.await()
            val birds = deferredBirdFetch.await()
            var zooAnimals = animals + birds
            //zooAnimals = zooAnimals.shuffled()

            zooAnimals.forEach {
                Log.d(TAG, "fetchZooAnimal-  $it")
            }
        }
    }

    fun fetchZooAnimalsRace() {
        lifecycleScope.launch {

            val deferredFetchAnimal = async(Dispatchers.IO) { fetchAnimals() }
            val deferredFetchBirds = async(Dispatchers.Default) { fetchBirds() }

            val zooAnimalFirstFetched = select {
                deferredFetchAnimal.onAwait {
                    deferredFetchBirds.cancel()
                    it
                }
                deferredFetchBirds.onAwait {
                    deferredFetchAnimal.cancel()
                    it
                }
            }

            zooAnimalFirstFetched.forEach {
                Log.d(TAG, "zooAnimalFirstFetched -  $it")
            }


        }
    }

    suspend fun fetchAnimals(): List<String> {
        delay(300)
        return listOf("Lion", "Tiger", "Cat", "Dog", "Elephant", "Girrafe")
    }

    suspend fun fetchBirds(): List<String> {
        delay(2500)
        return listOf("Parrot", "Eagle", "Penguin", "Ostrich", "Swan", "Ostrich")
    }

    suspend fun fetchA(): Int {
        delay(2000)
        return 100
    }

    suspend fun fetchB(): Int {
        delay(3500)
        return 99
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoroutineStudyTheme {
        Greeting("Android")
    }
}