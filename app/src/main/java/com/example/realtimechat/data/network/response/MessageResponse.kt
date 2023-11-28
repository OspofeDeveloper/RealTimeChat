package com.example.realtimechat.data.network.response

import com.example.realtimechat.domain.model.MessageModel
import com.example.realtimechat.domain.model.UserModel

/** A diferetcia de MessageDto, este es el modelo que recibimos de Database */
data class MessageResponse (
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
) {
    fun toDomain(): MessageModel =
        MessageModel(
            msg = msg.orEmpty(),
            hour = hour ?: "no date",
            date = date.orEmpty(),
            user = UserModel(user?.userName ?: "Guess", user?.admin ?: false)
        )
}

data class UserResponse(val userName: String? = null, val admin: Boolean? = null)