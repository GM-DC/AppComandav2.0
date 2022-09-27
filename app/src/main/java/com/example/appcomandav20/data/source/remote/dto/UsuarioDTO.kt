package com.example.appcomandav20.data.source.remote.dto

import com.example.appcomandav20.domain.model.UsuarioDC


data class UsuarioDTO(
    val Codigo: String,
    val Nombre: String
)

fun UsuarioDTO.toUsuarioDC(): UsuarioDC {
    return  UsuarioDC(
        codUsuario = Codigo,
        nombreUsuario = Nombre
    )
}


