package com.example.appcomandav20.features.config.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appcomandav20.R
import com.example.appcomandav20.databinding.FragConfigBinding
import com.example.appcomandav20.features.users.presentation.ListUsuarioFragDirections
import com.example.appcomandav20.util.utils.Companion.PORT
import com.example.appcomandav20.util.utils.Companion.URLBASE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class ConfigFrag : Fragment() {

    private var _binding:FragConfigBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ConfigViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[ConfigViewModel::class.java]
            }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragConfigBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataStore()
    }

    private fun getDataStore() {
        viewModel.config.observe(viewLifecycleOwner){
            URLBASE = it.urlBase
            PORT = it.port
            nextFragment(URLBASE,PORT)
        }
    }

    private fun nextFragment(url:String,port:String) {
        if (url.isBlank() || port.isBlank()) {
            binding.content.isVisible = true
            eventsHandlers()
        }
        else{
            binding.content.isVisible = false
            findNavController().navigate(R.id.next_listusers)
        }
    }

    private fun eventsHandlers() {
        binding.btnGuardar.setOnClickListener {
            saveDataStore()
            findNavController().navigate(R.id.next_listusers)
        }
    }

    private fun saveDataStore() {
        with(binding){
            URLBASE = etUrlbase.text.toString()
            PORT = etPort.text.toString()
            viewModel.saveDataStore(URLBASE,PORT)
        }
    }

}