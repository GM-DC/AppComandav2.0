package com.example.appcomandav20.view.ui.Usuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appcomandav20.R
import com.example.appcomandav20.databinding.FragPasscodeUsuarioBinding
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassCodeUsuarioFrag : Fragment() {

    private var _binding:FragPasscodeUsuarioBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: RecyclerUsuarioViewModel

    private val datosLoginUser = mutableListOf<LoginUserResponseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[RecyclerUsuarioViewModel::class.java]
            }!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragPasscodeUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNameWaiter()
        eventsHanlds()
        messageState()
    }

    private fun registrarDatos() {
        viewModel.loginResult.observe(viewLifecycleOwner){ it ->
            datosLoginUser.add(it)
            if (datosLoginUser.isNotEmpty()){
                datosLoginUser.clear()
                binding.txtCodigo.text = ""
                findNavController().navigate(R.id.next_activity)
            }
        }
    }

    private fun login() {
        val usuario = PassCodeUsuarioFragArgs.fromBundle(requireArguments()).nameMozo
        val password = binding.txtCodigo.text.toString()
        viewModel.loginUser(usuario = usuario, passwords = password)
        registrarDatos()
    }

    private fun initNameWaiter() {
        val name = PassCodeUsuarioFragArgs.fromBundle(requireArguments()).nameMozo
        binding.tvBienvenidaNombre.text = "BIENVENIDO $name"
    }

    private fun eventsHanlds() {
        binding.btn01.setOnClickListener { pressedNumber(binding.btn01.text.toString()) }
        binding.btn02.setOnClickListener { pressedNumber(binding.btn02.text.toString()) }
        binding.btn03.setOnClickListener { pressedNumber(binding.btn03.text.toString()) }
        binding.btn04.setOnClickListener { pressedNumber(binding.btn04.text.toString()) }
        binding.btn05.setOnClickListener { pressedNumber(binding.btn05.text.toString()) }
        binding.btn06.setOnClickListener { pressedNumber(binding.btn06.text.toString()) }
        binding.btn07.setOnClickListener { pressedNumber(binding.btn07.text.toString()) }
        binding.btn08.setOnClickListener { pressedNumber(binding.btn08.text.toString()) }
        binding.btn09.setOnClickListener { pressedNumber(binding.btn09.text.toString()) }
        binding.btn00.setOnClickListener { pressedNumber(binding.btn00.text.toString()) }

        binding.btnLimpiar.setOnClickListener { binding.txtCodigo.text = "" }

        binding.btnEntrar.setOnClickListener { login() }
    }

    private fun pressedNumber(digito: String) {
        binding.txtCodigo.text = "${binding.txtCodigo.text}${digito}"
    }

    private fun messageState() {
        viewModel.message.observe(viewLifecycleOwner){ it ->
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }

}