package com.example.appcomandav20.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcomandav20.databinding.ActivityUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsuarioActy : AppCompatActivity() {
    private lateinit var binding : ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}