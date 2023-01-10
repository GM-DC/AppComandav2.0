package com.example.appcomandav20.features.orders.data.remote.dto

import com.example.appcomandav20.features.orders.domain.model.ListDetalle
import com.example.appcomandav20.features.orders.domain.model.PreCount


data class PreCountDTO(
    var numerO_PEDIDO: String,
    var mesero: String,
    val zona: String,
    val mesa: String,
    val fechayhora: String,
    val observaciones: String?,
    val subtotal: String,
    val igv:String,
    val total: String,
    val detalle: List<ListDetalleDTO>
){
    fun toPreCount() = PreCount(
         numerO_PEDIDO = numerO_PEDIDO ,
         mesero= mesero ,
         zona= zona,
         mesa=  mesa,
         fechayhora= fechayhora ,
         observaciones= observaciones ,
         subtotal= subtotal ,
         igv=  igv,
         total= total ,
         detalle = detalle.map { it.toListDetalle() }
    )
}

data class ListDetalleDTO(
    val cantidad: Int,
    val nombre: String,
    val precio: Double,
    val importe: Double,
){
    fun toListDetalle() = ListDetalle(
         cantidad = cantidad,
         nombre= nombre,
         precio= precio,
         importe= importe,
    )
}