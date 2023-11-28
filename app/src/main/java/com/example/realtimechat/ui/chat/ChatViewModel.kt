package com.example.realtimechat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechat.domain.GetMessageUseCase
import com.example.realtimechat.domain.SendMessageUseCase
import com.example.realtimechat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
): ViewModel() {

    init {
        getMessages()
    }

    private var _state = MutableStateFlow<List<MessageModel>>(emptyList())
    val state: StateFlow<List<MessageModel>> = _state

    fun getMessages(){
        viewModelScope.launch {
            getMessageUseCase().collect{
                Log.d("Pozo", "la info es $it")
                _state.value = it
            }
        }
    }

    fun sendMessage() {
        val msg = "Hola buenas"
        sendMessageUseCase(msg)
    }
}