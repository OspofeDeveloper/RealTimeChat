package com.example.realtimechat.data.network.dto

/** Dto es Data Object, el modelo que le vamos a enviar a la BD externa (Como entity en Room) */
data class MessageDto (
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserDto
)

data class UserDto(val userName: String, val admin: Boolean)