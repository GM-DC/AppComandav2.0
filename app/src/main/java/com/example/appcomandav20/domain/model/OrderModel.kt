package com.example.appcomandav20.domain.model

data class OrderModel(
    val numerO_PEDIDO: String,
    val destino: String,
    val zona: String,
    val mesa: String,
    val mesero: String,
    val rutacomanda: String,
    val fechayhora: String,
    val detalle: List<OrderDetailModel>
)

data class OrderDetailModel(
    val iD_PRODUCTO: Int,
    val producto: String,
    val cantidad: Int,
    val precio: Double,
    val importe: Double,
    val observacion: String,
    val noM_IMP: String?,
    val secuencia: Int
)