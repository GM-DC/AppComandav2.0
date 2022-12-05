package com.example.appcomandav20.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.stringPreferencesKey
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class utils {

    companion object{
        var URLBASE = ""
        var PORT = ""
    }

    fun pricetostringformat(valuenumeric: Double): String {
        return String.format("%,.2f", valuenumeric)
    }

    fun priceSubTotal(price: Double): Double {
        //val igv = prefs.getIGV().toDouble()
        //(price.div(1 + (igv.div(100))))
        val montoBase = 100*price/118
        return montoBase
    }

    fun priceIGV(price: Double): Double {
        //val igv = prefs.getIGV().toDouble()
        // price.minus(price.div(1 + (igv.div(100))))
        val IGV = price*18/118
        return IGV
    }

    fun getFechaFormateado(): String {
        return SimpleDateFormat("dd/MM/yyyy").format(LocalDateTime.now())
    }

    fun getFecha(): String {
        return "${LocalDateTime.now()}"
    }

    fun fechaActual(): LocalDateTime {
        val fechaActual = LocalDateTime.now()
        val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val fechaSalida = fechaActual.format(dtf)

        return LocalDateTime.parse(fechaSalida)
    }

    fun formatearFecha(fecha:String):String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

        val d: Date = sdf.parse(fecha)
        val formattedTime = output.format(d)

        return formattedTime
    }

}