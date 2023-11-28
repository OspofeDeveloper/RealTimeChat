package com.example.realtimechat.domain.usecases

import android.icu.util.Calendar
import com.example.realtimechat.data.network.FirebaseChatService
import com.example.realtimechat.data.network.dto.MessageDto
import com.example.realtimechat.data.network.dto.UserDto
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val firebaseChatService: FirebaseChatService
) {
    operator fun invoke(msg: String, userName: String) {
        val calendar = Calendar.getInstance()
        val userDto = UserDto(userName, false)

        calendar.apply {
            val messageDto = MessageDto(
                msg = msg,
                hour = "${get(Calendar.HOUR_OF_DAY) + 1}:${get(Calendar.MINUTE)}",
                date = "${get(Calendar.YEAR)}/${get(Calendar.MONTH) + 1}/${get(Calendar.DAY_OF_MONTH)}",
                user = userDto
            )

            firebaseChatService.sendMsgToFirebase(messageDto = messageDto)
        }
    }
}