package com.example.appcomandav20.features.orders.domain.model

data class DishModel(
    val iD_PRODUCTO: Int,
    val codigo: String,
    val nombre: String,
    val simbolo: String,
    val preciO_VENTA: Double,
    val receta: Int,
    val adicional: String,
    val comanda: String,
    val igv: String,
    val psigv: String
)
