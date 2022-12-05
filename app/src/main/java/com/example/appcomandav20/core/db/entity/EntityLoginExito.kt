package com.example.appcomandav20.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appcomandav20.features.passcode.domain.model.LoginUserResponseModel

@Entity
data class EntityLoginExito(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val cdgmoneda: String,
    @ColumnInfo
    val cdgpago: String,
    @ColumnInfo
    val codigO_EMPRESA: String,
    @ColumnInfo
    val descuento: String,
    @ColumnInfo
    val estadopedido: String,
    @ColumnInfo
    val facturA_ADELANTADA: String,
    @ColumnInfo
    val iD_CLIENTE: Int,
    @ColumnInfo
    val iD_COTIZACION: String,
    @ColumnInfo
    val jwtToken: String,
    @ColumnInfo
    val nombreMozo: String,
    @ColumnInfo
    val nombreUsuario: String,
    @ColumnInfo
    val poR_IGV: String,
    @ColumnInfo
    val puntO_VENTA: String,
    @ColumnInfo
    val redondeo: String,
    @ColumnInfo
    val refreshToken: String,
    @ColumnInfo
    val seriepedido: String,
    @ColumnInfo
    val sucursal: String,
    @ColumnInfo
    val tipocambio: String,
    @ColumnInfo
    val usuario: String,
    @ColumnInfo
    val usuarioautoriza: String,
    @ColumnInfo
    val usuariocreacion: String,
    @ColumnInfo
    val validez: String,
    @ColumnInfo
    val cdG_VENDEDOR: String
)

fun EntityLoginExito.toLoginUserResponseModel(): LoginUserResponseModel {
    return LoginUserResponseModel(
        cdgmoneda= cdgmoneda,
        cdgpago= cdgpago,
        codigO_EMPRESA= codigO_EMPRESA,
        descuento= descuento,
        estadopedido= estadopedido,
        facturA_ADELANTADA= facturA_ADELANTADA,
        iD_CLIENTE= iD_CLIENTE,
        iD_COTIZACION= iD_COTIZACION,
        jwtToken= jwtToken,
        nombreMozo= nombreMozo,
        nombreUsuario= nombreUsuario,
        poR_IGV= poR_IGV,
        puntO_VENTA= puntO_VENTA,
        redondeo= redondeo,
        refreshToken= refreshToken,
        seriepedido= seriepedido,
        sucursal= sucursal,
        tipocambio= tipocambio,
        usuario= usuario,
        usuarioautoriza= usuarioautoriza,
        usuariocreacion= usuariocreacion,
        validez= validez,
        cdG_VENDEDOR = cdG_VENDEDOR)
}