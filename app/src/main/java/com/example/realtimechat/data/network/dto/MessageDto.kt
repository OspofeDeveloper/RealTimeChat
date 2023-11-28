package com.example.realtimechat.data.network.dto

/** Dto es Data Transfer Object, el modelo que le vamos a enviar a la BD externa
 *  (Como entity en Room). Estos son los datos que enviamos a Database */
data class MessageDto (
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserDto
)

data class UserDto(val userName: String, val admin: Boolean)