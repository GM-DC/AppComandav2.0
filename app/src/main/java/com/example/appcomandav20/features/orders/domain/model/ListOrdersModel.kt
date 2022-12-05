package com.example.appcomandav20.features.orders.domain.model

data class ListOrdersModel(
    var cantidad:Int,
    val namePlato:String,
    val categoria:String,
    val precio:Double,
    var precioTotal:Double,
    var observacion:String,
    var estadoPedido:String,
    var idProducto:Int,
    var camanda:String,
    val igv: Double,
    val psigv: Double,
    val flag_color:Int,
)