package com.example.realtimechat.domain

import com.example.realtimechat.data.network.FirebaseChatService
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val firebaseChatService: FirebaseChatService
) {
    operator fun invoke(msg: String) {
        firebaseChatService.sendMsgToFirebase(msg)
    }
}