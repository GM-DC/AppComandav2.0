package com.example.appcomandav20.features.orders.data.remote.dto

import com.example.appcomandav20.features.orders.domain.model.DishModel

data class DishDTO(
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

fun DishDTO.toDishModel(): DishModel {
    return  DishModel(
    iD_PRODUCTO = iD_PRODUCTO,
    codigo = codigo,
    nombre = nombre,
    simbolo = simbolo,
    preciO_VENTA = preciO_VENTA,
    receta = receta,
    adicional = adicional,
    comanda = comanda,
    igv = igv,
    psigv = psigv,
    )
}