package com.example.appcomandav20.features.users.data.remote.dto

import com.example.appcomandav20.features.users.domain.model.UsuarioDC


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


