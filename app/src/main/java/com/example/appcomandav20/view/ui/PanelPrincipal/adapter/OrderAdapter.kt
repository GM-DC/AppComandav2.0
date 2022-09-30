package com.example.appcomandav20.view.ui.PanelPrincipal.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.domain.model.DishModel
import com.example.appcomandav20.domain.model.ListOrdersModel

class OrderAdapter(private val data: MutableList<ListOrdersModel>, private val onClickListener: (ListOrdersModel) -> Unit): RecyclerView.Adapter<OrderAdapter.holderPedido>() {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderPedido {
        val layoutInflater = LayoutInflater.from(parent.context)
        return holderPedido(layoutInflater.inflate(R.layout.item_order,parent,false))
    }

    override fun onBindViewHolder(holder: holderPedido, position: Int) {
        holder.render(data[position],onClickListener)

        holder.itemView.setBackgroundResource(R.drawable.item_background) //negro

        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.item_select_background) //banco
            selectedPosition = -1
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

    class holderPedido(private val view: View): RecyclerView.ViewHolder(view){
        fun render (data: ListOrdersModel, onClickListener: (ListOrdersModel) -> Unit) {

            //******* DECLARANDO COMPONENTES *****
            val tv_nombrePlato = view.findViewById<TextView>(R.id.tv_nombrePlato)
            val tv_cantidad = view.findViewById<TextView>(R.id.tv_cantidad)
            val tv_precio = view.findViewById<TextView>(R.id.tv_precio)
            val tv_precioTotal = view.findViewById<TextView>(R.id.tv_precioTotal)

            val tv_nota = view.findViewById<TextView>(R.id.tv_nota)

            val precio = view.findViewById<TextView>(R.id.precio)
            val cantidad = view.findViewById<TextView>(R.id.cantidad)
            val total = view.findViewById<TextView>(R.id.total)


            if(data.estadoPedido=="PENDIENTE"){
                tv_nombrePlato.setTextColor(Color.parseColor("#11468F"))
                tv_precio.setTextColor(Color.parseColor("#11468F"))
                tv_precioTotal.setTextColor(Color.parseColor("#11468F"))
                tv_cantidad.setTextColor(Color.parseColor("#11468F"))
                precio.setTextColor(Color.parseColor("#11468F"))
                cantidad.setTextColor(Color.parseColor("#11468F"))
                total.setTextColor(Color.parseColor("#11468F"))
                tv_nota.setTextColor(Color.parseColor("#11468F"))
            }else{
                tv_nombrePlato.setTextColor(Color.parseColor("#DA1212"))
                tv_precio.setTextColor(Color.parseColor("#DA1212"))
                tv_precioTotal.setTextColor(Color.parseColor("#DA1212"))
                tv_cantidad.setTextColor(Color.parseColor("#DA1212"))
                precio.setTextColor(Color.parseColor("#DA1212"))
                cantidad.setTextColor(Color.parseColor("#DA1212"))
                total.setTextColor(Color.parseColor("#DA1212"))
                tv_nota.setTextColor(Color.parseColor("#DA1212"))
            }

            if (data.observacion==""){
                tv_nota.text = ""
            }else{
                tv_nota.text = "N"
            }


            //************ ASIGNANDO COMPONENTES ********
            tv_cantidad.text = "${data.cantidad}"

            tv_nombrePlato.text = data.namePlato
            tv_precio.text = "${data.precio}"
            data.precioTotal = data.precio*data.cantidad
            tv_precioTotal.text = "${data.precioTotal}"

        }


    }

    fun setItems(list: MutableList<ListOrdersModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}

