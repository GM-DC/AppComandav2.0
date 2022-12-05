package com.example.appcomandav20.features.orders.data.remote.dto

import com.example.appcomandav20.features.orders.domain.model.OrderDetailModel
import com.example.appcomandav20.features.orders.domain.model.OrderModel

data class OrderDTO(
    val numerO_PEDIDO: String,
    val destino: String,
    val zona: String,
    val mesa: String,
    val mesero: String,
    val rutacomanda: String,
    val fechayhora: String,
    val detalle: List<OrderDetailDTO>
)

data class OrderDetailDTO(
    val iD_PRODUCTO: Int,
    val producto: String,
    val cantidad: Int,
    val precio: Double,
    val importe: Double,
    val observacion: String,
    val noM_IMP: String?,
    val secuencia: Int
)


fun OrderDTO.toOrderModel(): OrderModel {

    val detalle = detalle.map { entries ->
        OrderDetailModel(
        iD_PRODUCTO= entries.iD_PRODUCTO,
        producto= entries.producto,
        cantidad= entries.cantidad,
        precio= entries.precio,
        importe=entries.importe,
        observacion=entries.observacion,
        noM_IMP= entries.noM_IMP,
        secuencia= entries.secuencia)
    }

    return OrderModel(
    numerO_PEDIDO= numerO_PEDIDO ,
    destino= destino,
    zona= zona,
    mesa=mesa ,
    mesero= mesero,
    rutacomanda= rutacomanda,
    fechayhora= fechayhora,
    detalle= detalle,)

}
