package com.dicoding.jetpack.academy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.jetpack.academy.R
import com.dicoding.jetpack.academy.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPager = SectionPagerAdapter(this, supportFragmentManager)
        binding.vp.adapter = sectionPager
        binding.tabs.setupWithViewPager(binding.vp)
        supportActionBar?.elevation = 0f
    }
}