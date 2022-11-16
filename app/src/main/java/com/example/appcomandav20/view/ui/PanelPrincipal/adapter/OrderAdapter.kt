package com.example.appcomandav20.view.ui.PanelPrincipal.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.databinding.ItemOrderBinding
import com.example.appcomandav20.domain.model.ListOrdersModel


class OrderAdapter(
    private val data: MutableList<ListOrdersModel>,
    private val itemClickIncrease: (Int) -> Unit,
    private val itemClickDiminish: (Int) -> Unit,
    private val itemClickObservation: (Int) -> Unit
): RecyclerView.Adapter<OrderAdapter.holderPedido>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderPedido {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderPedido(layoutInflater.inflate(R.layout.item_order,parent,false))
    }

    override fun onBindViewHolder(holder: holderPedido, position: Int) {
        holder.render(data[position],position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class holderPedido(private val view: View): RecyclerView.ViewHolder(view){
        val binding = ItemOrderBinding.bind(view)
        fun render (data: ListOrdersModel, position: Int) {

            //************ ASIGNANDO COMPONENTES ********
            binding.tvCantidad.text = data.cantidad.toString()
            binding.tvNombrePlato.text = data.namePlato
            binding.tvPrecio.text = "${data.precio}"
            data.precioTotal = data.precio*data.cantidad
            binding.tvPrecioTotal.text = "${data.precioTotal}"
            if(data.estadoPedido=="PENDIENTE"){
                binding.tvNombrePlato.setTextColor(Color.parseColor("#11468F"))
                binding.tvPrecio.setTextColor(Color.parseColor("#11468F"))
                binding.tvPrecioTotal.setTextColor(Color.parseColor("#11468F"))
                binding.tvCantidad.setTextColor(Color.parseColor("#11468F"))
                binding.precio.setTextColor(Color.parseColor("#11468F"))
                binding.total.setTextColor(Color.parseColor("#11468F"))
                binding.tvNota.setTextColor(Color.parseColor("#11468F"))
            }else{
                binding.ivBtnDisminuir.isVisible = false
                binding.ivBtnAumentar.isVisible = false
                binding.ivBtnObservation.isVisible = false
                binding.tvNombrePlato.setTextColor(Color.parseColor("#DA1212"))
                binding.tvPrecio.setTextColor(Color.parseColor("#DA1212"))
                binding.tvPrecioTotal.setTextColor(Color.parseColor("#DA1212"))
                binding.tvCantidad.setTextColor(Color.parseColor("#DA1212"))
                binding.precio.setTextColor(Color.parseColor("#DA1212"))
                binding.total.setTextColor(Color.parseColor("#DA1212"))
                binding.tvNota.setTextColor(Color.parseColor("#DA1212"))
            }
            if (data.observacion==""){
                binding.tvNota.text = ""
            }else{
                binding.tvNota.text = "N"
            }
            var lastValue = ""

            binding.ivBtnAumentar.setOnClickListener { itemClickIncrease(position) }
            binding.ivBtnDisminuir.setOnClickListener { itemClickDiminish(position) }
            binding.ivBtnObservation.setOnClickListener { itemClickObservation(position) }
        }

    }

    fun setItems(list: MutableList<ListOrdersModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }





}

