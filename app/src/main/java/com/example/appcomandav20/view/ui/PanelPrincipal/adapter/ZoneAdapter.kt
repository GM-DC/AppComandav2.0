package com.example.appcomandav20.view.ui.PanelPrincipal.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.domain.model.ZoneModel

class ZoneAdapter (private val data: MutableList<ZoneModel>, private val onClickListener: (ZoneModel) -> Unit): RecyclerView.Adapter<ZoneAdapter.holderZona>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderZona {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderZona(layoutInflater.inflate(R.layout.item_zona,parent,false))
    }

    override fun onBindViewHolder(holder: holderZona, position: Int) {
        holder.render(data[position], onClickListener, position)

        if (selectedPosition == position) {
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

    class holderZona(private val view: View): RecyclerView.ViewHolder(view){
        fun render (data: ZoneModel, onClickListener: (ZoneModel) -> Unit, position: Int){
            val tx_zona = view.findViewById<TextView>(R.id.tx_zona)

            tx_zona.text = data.nombreZonas
            tx_zona.setTextColor(Color.parseColor("#0E83C9"))

        }
    }
    fun setItems(list: MutableList<ZoneModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}