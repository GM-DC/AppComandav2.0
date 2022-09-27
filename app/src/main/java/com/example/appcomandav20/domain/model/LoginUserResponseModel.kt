package com.example.appcomandav20.domain.model

data class LoginUserResponseModel(
    val cdgmoneda: String,
    val cdgpago: String,
    val codigO_EMPRESA: String,
    val descuento: String,
    val estadopedido: String,
    val facturA_ADELANTADA: String,
    val iD_CLIENTE: Int,
    val iD_COTIZACION: String,
    val jwtToken: String,
    val nombreMozo: String,
    val nombreUsuario: String,
    val poR_IGV: String,
    val puntO_VENTA: String,
    val redondeo: String,
    val refreshToken: String,
    val seriepedido: String,
    val sucursal: String,
    val tipocambio: String,
    val usuario: String,
    val usuarioautoriza: String,
    val usuariocreacion: String,
    val validez: String,
    val cdG_VENDEDOR: String
)