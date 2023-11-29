package com.example.realtimechat.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechat.domain.usecases.GetMessageUseCase
import com.example.realtimechat.domain.usecases.SendMessageUseCase
import com.example.realtimechat.domain.model.MessageModel
import com.example.realtimechat.domain.usecases.GetUserNameUseCase
import com.example.realtimechat.domain.usecases.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logOutUseCase: LogOutUseCase
): ViewModel() {

    private var _state = MutableStateFlow<List<MessageModel>>(emptyList())
    val state: StateFlow<List<MessageModel>> = _state

    var name: String = ""

    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUserNameUseCase()
        }
    }

    private fun getMessages(){
        viewModelScope.launch {
            getMessageUseCase().collect{
                _state.value = it
            }
        }
    }

    fun sendMessage(msg: String) {
        viewModelScope.launch {
            sendMessageUseCase(msg, name)
        }
    }

    /** Si necesitamos el resultado de una corrutina para hacer algo en UI tenemos
     *  que hacer la corrutina asincrona y esperar a que acabe. Sino corremos el riesgo
     *  de que la corrutina tarde mas de lo que tardamos en llegar a la operacion que
     *  necesita su resultado y por ende esta operación en main thread no se hará */
    fun logOut(onViewFinished: () -> Unit) {
        viewModelScope.launch {
            async { logOutUseCase() }.await()
            onViewFinished()
        }
    }
}