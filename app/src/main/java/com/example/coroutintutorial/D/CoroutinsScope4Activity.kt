package com.example.coroutintutorial.D

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.coroutintutorial.R
import kotlinx.coroutines.*

class CoroutinsScope4Activity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting Coroutins in Thread: ${Thread.currentThread().name}")
            val answer = doTheNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in Thread: ${Thread.currentThread().name}")
                textView.text = answer
            }
        }
    }

    suspend fun doTheNetworkCall(): String {
        delay(6000L)
        return "This is the answer "
    }

}

/*Coroutin scope build a new Thread.
* Corutin will be canceled when Main Thread is canceled.
* Contexts in Coroutine describe that Coroutins start in which Thread
*
* important Coroutin contexts:
* Main --> in  the Main Thread
* IO --> this context use for pi or network and something stuff ...
* with "withContext()" we can change my Threads */