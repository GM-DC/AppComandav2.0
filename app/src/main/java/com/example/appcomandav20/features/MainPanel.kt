package com.example.appcomandav20.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcomandav20.databinding.ActivityMainPanelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPanel : AppCompatActivity() {
    private lateinit var binding : ActivityMainPanelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}