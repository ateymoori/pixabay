package com.pixabay.ui.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pixabay.R
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("dddd 1", "")
    }

}
