package com.example.appcomandav20.data.source.remote.response

import com.example.appcomandav20.domain.model.LoginUserResponseModel

data class LoginUserResponse(
    val cdgmoneda: String,
    val cdgpago: String,
    val codigO_EMPRESA: String,
    val correlativo: Any,
    val descuento: String,
    val estadopedido: String,
    val facturA_ADELANTADA: String,
    val iD_CLIENTE: Int,
    val iD_COTIZACION: String,
    val jwtToken: String,
    val motivo: Any,
    val nombremozo: String,
    val nombreusuario: String,
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

fun LoginUserResponse.toLoginUserResponseModel(): LoginUserResponseModel {
    return LoginUserResponseModel(
        cdgmoneda = cdgmoneda,
        cdgpago = cdgpago,
        codigO_EMPRESA = codigO_EMPRESA,
        descuento = descuento,
        estadopedido = estadopedido,
        facturA_ADELANTADA = facturA_ADELANTADA,
        iD_CLIENTE = iD_CLIENTE,
        iD_COTIZACION = iD_COTIZACION,
        jwtToken = jwtToken,
        nombreMozo = nombremozo,
        nombreUsuario = nombreusuario,
        poR_IGV = poR_IGV,
        puntO_VENTA = puntO_VENTA,
        redondeo = redondeo,
        refreshToken = refreshToken,
        seriepedido = seriepedido,
        sucursal = sucursal,
        tipocambio = tipocambio,
        usuario = usuario,
        usuarioautoriza = usuarioautoriza,
        usuariocreacion = usuariocreacion,
        validez = validez,
        cdG_VENDEDOR = cdG_VENDEDOR,
    )
}