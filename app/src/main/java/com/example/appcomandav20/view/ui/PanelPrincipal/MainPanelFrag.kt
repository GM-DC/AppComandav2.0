package com.example.appcomandav20.view.ui.PanelPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.appcomandav20.databinding.FragMainPanelBinding
import com.example.appcomandav20.view.ui.viewmodels.MainPanelViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainPanelFrag : Fragment() {
    private var _binding: FragMainPanelBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainPanelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[MainPanelViewModel::class.java]
            }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragMainPanelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}