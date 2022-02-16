package com.example.coroutintutorial.E

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutintutorial.R
import kotlinx.coroutines.*

class RunBlocking5Activity : AppCompatActivity() {

    private val TAG = "RunBlocking5Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.runblocking5_main2)

        Log.d(TAG, "Before runBlocking: ${Thread.currentThread().name}")
        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutin 1: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "finished IO Coroutins 2: ${Thread.currentThread().name}")
            }
            Log.d(TAG, "Start of  runBlocking: ${Thread.currentThread().name}")
            delay(5000L)
            Log.d(TAG, "End of runBlocking: ${Thread.currentThread().name}")
        }
        Log.d(TAG, "After runBlocking: ${Thread.currentThread().name}")
    }
}

/*run blocking can block the MainThread but GlobalScope.launch(Dispatchers.Main) can't
* for example if we used delay() in GlobalScope after that we can update the UI but if we use it in runBlocking,
* we can't update the UI.
*
* important note : both of runBlocking on top, start --> "at the same time" <-- time and not start first runBlocking 1 and then,
* after 6 seconds start runBlocking 2
*
* so runBlocking effect just on MainThread*/