package com.example.appcomandav20.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.domain.model.DishModel
import kotlin.collections.ArrayList

class DishAdapter(var data: MutableList<DishModel>, private val onClickListener: (DishModel) -> Unit): RecyclerView.Adapter<DishAdapter.holderPlato>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderPlato {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderPlato(layoutInflater.inflate(R.layout.item_dish,parent,false))
    }

    override fun onBindViewHolder(holder: holderPlato, position: Int) {
        holder.render(data[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class holderPlato(private val view: View): RecyclerView.ViewHolder(view){
        fun render (data: DishModel, onClickListener: (DishModel) -> Unit){
            val tx_plato = view.findViewById<TextView>(R.id.tx_plato)
            val tx_platoPrecio = view.findViewById<TextView>(R.id.tx_platoPrecio)

            tx_platoPrecio.text = "S/. ${data.preciO_VENTA}"
            tx_plato.text = data.nombre
            tx_platoPrecio.setTextColor(Color.parseColor("#0E83C9"))
            tx_plato.setTextColor(Color.parseColor("#0E83C9"))

            itemView.setOnClickListener{onClickListener(data)}
        }
    }

    fun filterList(superHeroNames: MutableList<DishModel>) {
        data = superHeroNames
        notifyDataSetChanged()
    }

    fun setItems(list: MutableList<DishModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}