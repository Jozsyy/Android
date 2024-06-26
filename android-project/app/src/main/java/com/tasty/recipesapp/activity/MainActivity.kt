package com.tasty.recipesapp.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationBarView
import com.tasty.recipesapp.R
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


        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                    return@OnItemSelectedListener true
                }
                R.id.recipesFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.recipesFragment)
                    return@OnItemSelectedListener true
                }
                R.id.profileFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                    return@OnItemSelectedListener true
                }
                R.id.wishlistFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.wishlistFragment)
                    return@OnItemSelectedListener true
                }

                else -> true
            }

        })
    }
}