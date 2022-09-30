package com.example.appcomandav20.view.ui.PanelPrincipal.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.domain.model.CategoryModel

class CategoryAdapter(private val data: MutableList<CategoryModel>, private val onClickListener: (CategoryModel) -> Unit): RecyclerView.Adapter<CategoryAdapter.holderCategoria>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderCategoria {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderCategoria(layoutInflater.inflate(R.layout.item_category,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: holderCategoria, position: Int) {
        holder.render(data[position], onClickListener)

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

    class holderCategoria(private val view: View): RecyclerView.ViewHolder(view){
        fun render (data: CategoryModel, onClickListener: (CategoryModel) -> Unit){
            val tx_categoria = view.findViewById<TextView>(R.id.tx_categoria)

            tx_categoria.text = data.nameCategoria

        }
    }

    fun setItems(list: MutableList<CategoryModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}