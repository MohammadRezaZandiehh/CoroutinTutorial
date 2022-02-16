package com.example.coroutintutorial.G

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutintutorial.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class AsyncAndAwait7Activity : AppCompatActivity() {

    private val TAG = "RunBlocking5Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.async_await_activity)
/*
* in halatie ko async nist . yani vagti kamelan methode aval tamom shod , badesh methode dovom ejra mishe
* na inke b tore ham zaman yeksan anjam shavand
*
       GlobalScope.launch (Dispatchers.IO){
           val time = measureTimeMillis {
               val answer1 = networkCall1()
               val answer2 = networkCall2()

               Log.d(TAG, "Answer1 is $answer1")
               Log.d(TAG, "Answers is $answer2")
           }
           Log.d(TAG, "Requests took $time ms. ")
       }
*/
/*
* inam ye raveshie vase async sakhtan k goft raveshe khobis nis
* vali ejra konim hamzaman ejra mishan
        GlobalScope.launch (Dispatchers.IO){
            val time = measureTimeMillis {
                var answer1 :String? = null
                var answer2 :String? = null

                val job1 = launch { answer1 = networkCall1() }
                val job2 = launch { answer2 = networkCall2() }

                job1.join()
                job2.join()

                Log.d(TAG, "Answer1 is $answer1")
                Log.d(TAG, "Answer2 is $answer2")
            }
            Log.d(TAG, "Requests took $time ms. ")
        }
*/

/*
* in behtarin ravesh baraye estefade az --> async <--  ast.
* */
        GlobalScope.launch (Dispatchers.IO){
            val time = measureTimeMillis {

                var answer1 = async { networkCall1() }
                var answer2 = async { networkCall2() }

                Log.d(TAG, "Answer1 is ${answer1.await()}")
                Log.d(TAG, "Answer2 is ${answer2.await()}")
            }
            Log.d(TAG, "Requests took $time ms. ")
        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }
}