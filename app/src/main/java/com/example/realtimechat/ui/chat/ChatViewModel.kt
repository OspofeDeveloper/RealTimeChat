package com.example.realtimechat.ui.chat

import androidx.lifecycle.ViewModel
import com.example.realtimechat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase
): ViewModel() {

    fun sendMessage() {
        val msg = "Hola buenas"
        sendMessageUseCase(msg)
    }
}