package com.tasty.recipesapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tasty.recipesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Log.d(TAG, "##onCreate: MainActivity created.")

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))    //vagy az elozo sor setContentView


//        val result = intent.getStringExtra("justAtext")
//        binding.textViewMain.text= result
//        Log.d(TAG,"##MainAactivity - result = $result")
    }
}