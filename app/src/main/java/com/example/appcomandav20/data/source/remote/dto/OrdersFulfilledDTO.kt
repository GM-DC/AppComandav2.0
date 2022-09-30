package com.example.appcomandav20.data.source.remote.dto

import com.example.appcomandav20.domain.model.ListOrdersModel
import com.example.appcomandav20.domain.model.TableModel

class DCPedidoMesa : ArrayList<OrdersFulfilledDTO>()

data class OrdersFulfilledDTO(
    val iD_PEDIDO: Int,
    val iD_PRODUCTO: Int,
    val cantidad: Int,
    val nombre: String,
    val precio: Double,
    val descuento: Int,
    val igv: Double,
    val importe: Double,
    val canT_DESPACHADA: Int,
    val canT_FACTURADA: Int,
    val observacion: String,
    val secuencia: Int,
    val preciO_ORIGINAL: Double,
    val tipo: String,
    val importE_DSCTO: Double,
    val afectO_IGV: String,
    val comision: Int,
    val iD_PRESUPUESTO: Int,
    val cdG_SERV: Any,
    val flaG_C: Any,
    val flaG_P: Any,
    val flaG_COLOR: Int,
    val noM_UNIDAD: Any,
    val comanda: String,
    val mozo: Any,
    val unidad: String,
    val codigO_BARRA: String,
    val poR_PERCEPCION: Any,
    val percepcion: Any,
    val valoR_VEN: Any,
    val uniD_VEN: Any,
    val fechA_VEN: Any,
    val factoR_CONVERSION: Any,
    val cdG_KIT: Any,
    val swT_PIGV: String,
    val swT_PROM: String,
    val canT_KIT: Any,
    val swT_DCOM: String,
    val swT_SABOR: String,
    val swT_FREE: String,
    val noM_IMP: Any,
    val seC_PROD: Any,
    val poR_DETRACCION: Any,
    val detraccion: Any,
    val usuariO_ANULA: Any,
    val fechA_ANULA: Any,
    val margen: Any,
    val importE_MARGEN: Any,
    val costO_ADIC: Any
)


fun OrdersFulfilledDTO.toListOrdersModel(): ListOrdersModel {
    return  ListOrdersModel(
        cantidad= cantidad,
        namePlato= nombre,
        categoria= "",
        precio= precio,
        precioTotal= importe,
        observacion= "",
        estadoPedido= "ATENDIDO",
        idProducto= iD_PRODUCTO,
        camanda= comanda,
        igv= igv,
        psigv= 0.0,
        flag_color= flaG_COLOR,
    )
}

