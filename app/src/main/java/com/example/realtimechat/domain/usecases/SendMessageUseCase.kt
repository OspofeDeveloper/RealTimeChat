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

        val hour = getCorrectHour(calendar)
        val date = getCorrectDate(calendar)

        val messageDto = MessageDto(
            msg = msg,
            hour = hour,
            date = date,
            user = userDto
        )

        firebaseChatService.sendMsgToFirebase(messageDto = messageDto)

    }

    private fun getCorrectHour(calendar: Calendar): String {
        val hour = calendar.get(Calendar.HOUR_OF_DAY) + 1
        val minute = calendar.get(Calendar.MINUTE)

        val formattedHour = if (hour < 10) String.format("0%d", hour) else hour.toString()
        val formattedMinute = if (minute < 10) String.format("0%d", minute) else minute.toString()

        return String.format("%s:%s", formattedHour, formattedMinute)
    }

    private fun getCorrectDate(calendar: Calendar): String {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val formattedMonth = if (month < 10) String.format("0%d", month) else month.toString()
        val formattedDay = if (day < 10) String.format("0%d", day) else day.toString()

        return String.format("%d/%s/%s", year, formattedMonth, formattedDay)
    }
}