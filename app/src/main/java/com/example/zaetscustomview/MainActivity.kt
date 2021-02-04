package com.example.zaetscustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zaetscustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListener()

        binding.movieCard.setRating(3.5f)
        binding.movieCard.setMovieName(R.string.movie_name)
    }

    private fun setupListener() {
        binding.fanControl.setOnClickListener {
            it as FanControlView
            binding.fan.speed = it.currentStep * 3
        }
    }
}