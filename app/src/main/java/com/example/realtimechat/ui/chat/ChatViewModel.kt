package com.example.realtimechat.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechat.domain.usecases.GetMessageUseCase
import com.example.realtimechat.domain.usecases.SendMessageUseCase
import com.example.realtimechat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
): ViewModel() {

    private var _state = MutableStateFlow<List<MessageModel>>(emptyList())
    val state: StateFlow<List<MessageModel>> = _state

    init {
        getMessages()
    }

    private fun getMessages(){
        viewModelScope.launch {
            getMessageUseCase().collect{
                _state.value = it
            }
        }
    }

    fun sendMessage() {
        val msg = "Hola buenas"
        sendMessageUseCase(msg)
    }
}