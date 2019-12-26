package com.pixabay.ui.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pixabay.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            main()
        }
        
    }

    suspend fun main() = coroutineScope {
        launch {
            delay(4000)
            println("print__11")
            test()
        }
        println("print__22")
    }

    fun test() {
        Toast.makeText(this, "ddddd", Toast.LENGTH_SHORT).show()
    }

}
