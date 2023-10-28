package com.tasty.recipesapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.tasty.recipesapp.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SplashActivity", "##onCreate called")
        //setContentView(R.layout.activity_splash)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView((binding.root))    //vagy az elozo sor setContentView

//        binding.startButton.setOnClickListener{
//            Log.d(TAG, "##startButton onClick")
//            val intent = Intent(this, MainActivity::class.java)
//            val text = binding.editTextText.getText().toString()
//            intent.putExtra("justAtext",text)
//            startActivity(intent)
//        }

        // Use a HandlerThread to create a background thread
        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        val SPLASH_TIME_OUT:Long=2000;
        handler.postDelayed({
        // Navigate to MainActivity after the delay
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() },
            SPLASH_TIME_OUT)


    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity", "##onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SplashActivity", "##onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SplashActivity", "##onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SplashActivity", "##onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "##onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SplashActivity", "##onRestart called")
    }
}