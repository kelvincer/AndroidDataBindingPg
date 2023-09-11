package com.keliceru.challengeprosegur.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.keliceru.challengeprosegur.R
import com.keliceru.challengeprosegur.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = binding.fragmentContainer.getFragment<NavHostFragment>().navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.lifecycleOwner = this
    }
}