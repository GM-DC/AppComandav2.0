package com.example.appcomandav20.features.users.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.databinding.FragListUsuarioBinding
import com.example.appcomandav20.features.users.domain.model.UsuarioDC
import com.example.appcomandav20.features.users.presentation.adapter.UsuarioAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUsuarioFrag : Fragment() {

    private var _binding:FragListUsuarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioAdapter: UsuarioAdapter
    lateinit var viewModel: RecyclerUsuarioViewModel

    private val itemList = mutableListOf<UsuarioDC>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[RecyclerUsuarioViewModel::class.java]
            }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragListUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniciarRecyclerview()
        getListaUsuario()
        stateProgress()
        messageState()
    }

    private fun iniciarRecyclerview() {
        binding.rvUsuarios.layoutManager = GridLayoutManager(activity,2, RecyclerView.VERTICAL,false)
        usuarioAdapter = UsuarioAdapter(itemList) {dataClassUsuario -> onItemSelected(dataClassUsuario)}
        binding.rvUsuarios.adapter = usuarioAdapter
    }

    private fun onItemSelected(dataClassUsuario: UsuarioDC) {
        val nombreMozo = dataClassUsuario.nombreUsuario
        val action = ListUsuarioFragDirections.nextAction()
            .setNameMozo(nombreMozo)
        findNavController().navigate(action)
    }

    private fun getListaUsuario() {
        viewModel.listState.observe(viewLifecycleOwner){ it ->
            usuarioAdapter.setItems(it)
        }
    }

    private fun stateProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner){ it ->
            binding.progress.isVisible = it
        }
    }

    private fun messageState() {
        viewModel.message.observe(viewLifecycleOwner){ it ->
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }
}