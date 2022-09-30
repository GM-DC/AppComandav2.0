package com.example.appcomandav20.view.ui.Usuario.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.domain.model.UsuarioDC


class UsuarioAdapter(val usuario: MutableList<UsuarioDC>, private val onClickListener: (UsuarioDC) -> Unit):RecyclerView.Adapter<UsuarioAdapter.holderUsuario>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderUsuario {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderUsuario(layoutInflater.inflate(R.layout.item_usuario,parent,false))
    }

    override fun onBindViewHolder(holder: holderUsuario, position: Int) {
        holder.render(usuario[position],onClickListener)
    }

    override fun getItemCount(): Int {
        return usuario.size
    }

    fun setItems(list: MutableList<UsuarioDC>) {
        usuario.clear()
        usuario.addAll(list)
        notifyDataSetChanged()
    }

    class holderUsuario(private val view: View):RecyclerView.ViewHolder(view){

        fun render (usuario: UsuarioDC, onClickListener: (UsuarioDC) -> Unit){
            val tv_usuario = view.findViewById<TextView>(R.id.tx_Nombre)
            val cv_usuario = view.findViewById<ImageView>(R.id.iv_iconUsuario)

            tv_usuario.text = usuario.nombreUsuario
            tv_usuario.setTextColor(Color.parseColor("#005098"))
            cv_usuario.setColorFilter(Color.parseColor("#005098"))

            itemView.setOnClickListener { onClickListener(usuario) }
        }
    }
}