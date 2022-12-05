package com.example.appcomandav20.features.zones.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.features.zones.domain.model.TableModel

class TableAdapter (
    private val data: MutableList<TableModel>,
    private val onClickListener: (TableModel) -> Unit)
    : RecyclerView.Adapter<TableAdapter.holderMesa>() {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderMesa {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderMesa(layoutInflater.inflate(R.layout.item_table,parent,false))
    }

    override fun onBindViewHolder(holder: holderMesa, position: Int) {
        holder.render(data[position],onClickListener, position)

        if (selectedPosition === position) {
            holder.itemView.setBackgroundResource(R.drawable.item_select_background) //banco
        }else {
            holder.itemView.setBackgroundResource(R.drawable.item_background) //negro
        }

        holder.itemView.setOnClickListener {
            onClickListener(data[position])
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return data.size

    }

    class holderMesa(private val view: View): RecyclerView.ViewHolder(view){
        fun render (data: TableModel, onClickListener: (TableModel) -> Unit, position: Int){
            val tx_mesa = view.findViewById<TextView>(R.id.tx_mesa)
            val tx_name = view.findViewById<TextView>(R.id.tx_name)

            if (data.estadoTrans == "L" && data.idPedido.isNullOrEmpty()){
                // Libre
                tx_mesa.text = "Mesa ${data.idMesa}"
                tx_name.text = data.NombreMozo
                tx_mesa.setTextColor(Color.parseColor("#0E83C9"))
                tx_name.setTextColor(Color.parseColor("#0E83C9"))
            }else if (data.estadoTrans == "O" && !data.idPedido.isNullOrEmpty()){
                // ocupado
                tx_mesa.text = "Mesa ${data.idMesa}"
                tx_name.text = data.NombreMozo
                tx_mesa.setTextColor(Color.parseColor("#D50000"))
                tx_name.setTextColor(Color.parseColor("#D50000"))
            }else{
                // ocupado
                tx_mesa.text = "Mesa ${data.idMesa}"
                tx_name.text = data.NombreMozo
                tx_mesa.setTextColor(Color.parseColor("#FF5722"))
                tx_name.setTextColor(Color.parseColor("#FF5722"))
            }

        }

    }

    fun setItems(list: MutableList<TableModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}