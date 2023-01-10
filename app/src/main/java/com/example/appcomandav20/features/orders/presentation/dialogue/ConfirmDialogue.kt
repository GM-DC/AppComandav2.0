package com.example.appcomandav20.features.orders.presentation.dialogue

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.appcomandav20.databinding.DialogueConfirmaComandaBinding

class ConfirmDialogue (
    private val onClick: (Boolean) -> Unit,
) : DialogFragment(){
    lateinit var binding : DialogueConfirmaComandaBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogueConfirmaComandaBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity()).setView(binding.root)

        eventsHandlets()

        val dialogue = builder.create()
        return dialogue
    }

    private fun eventsHandlets() {
        binding.btConfirmarcomanda.setOnClickListener { confirmComanda() }
        binding.btCancelarcomanda.setOnClickListener { cancelComanda() }
    }

    private fun cancelComanda() {
        onClick(false)
        dismiss()
    }

    private fun confirmComanda() {
        onClick(true)
        dismiss()
    }
}