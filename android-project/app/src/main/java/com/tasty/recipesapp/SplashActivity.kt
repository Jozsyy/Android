package com.tasty.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //setContentView((binding.root))    //vagy az elozo sor
    }

//    binding.startButton.setOnClickListener{
//        Log.d()
//         val
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "onDestroy called")
    }

    override fun onStart() {
        super.onStart()
    }
}