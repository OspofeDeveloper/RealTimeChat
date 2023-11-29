package com.example.realtimechat.domain.model

import com.example.realtimechat.data.network.dto.MessageDto
import com.example.realtimechat.data.network.dto.UserDto

data class MessageModel (
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserModel
) {
    fun toDto(): MessageDto =
        MessageDto(
            msg = msg,
            hour = hour,
            date = date,
            user = UserDto(user.userName, user.admin)
        )
}

data class UserModel(val userName: String, val admin: Boolean)