package com.example.realtimechat.domain

import com.example.realtimechat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {
    operator fun invoke() = firebaseChatService.getMessage()

}