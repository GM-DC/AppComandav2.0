package com.example.appcomandav20.view.ui.PanelPrincipal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcomandav20.databinding.ActivityMainBinding
import com.example.appcomandav20.databinding.ActyMainPanelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPanel : AppCompatActivity() {
    private lateinit var binding : ActyMainPanelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActyMainPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}