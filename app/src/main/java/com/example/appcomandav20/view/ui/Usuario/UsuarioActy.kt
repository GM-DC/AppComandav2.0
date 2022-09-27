package com.example.appcomandav20.view.ui.Usuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcomandav20.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsuarioActy : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}