package com.example.appcomandav20.features.orders.presentation.dialogue

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.appcomandav20.databinding.DialogueDetalleBinding
import com.example.appcomandav20.features.orders.domain.model.CategoryModel

class ObservationDialogue (
    private val detail: String,
    private val onClickSaveDetail: (String) -> Unit,
) : DialogFragment(){
    lateinit var binding : DialogueDetalleBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogueDetalleBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity()).setView(binding.root)

        initData()
        eventsHandlets()

        val dialogue = builder.create()
        return dialogue
    }

    private fun initData() {
        binding.etDetalle.setText(detail)
    }

    private fun eventsHandlets() {
        binding.btGuardarDetalle.setOnClickListener { editarDetalle() }
    }

    private fun editarDetalle() {
        val codigo = binding.etDetalle.text.toString()
        onClickSaveDetail(codigo)
        dismiss()
    }

}