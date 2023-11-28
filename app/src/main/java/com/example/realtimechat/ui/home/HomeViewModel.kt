package com.example.realtimechat.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechat.domain.usecases.GetUserNameUseCase
import com.example.realtimechat.domain.usecases.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<HomeViewState>(HomeViewState.LOADING)
    val state: StateFlow<HomeViewState> = _state

    init {
        verifyUserLogged()
    }

    private fun verifyUserLogged() {
        /** Si existe el nombre ya estamos loggeados asi que podemos evitar pasar por home*/
        viewModelScope.launch {
            val name = getUserNameUseCase()
            if(name.isNotEmpty()) {
                _state.value = HomeViewState.REGISTERED
            } else {
                _state.value = HomeViewState.UNREGISTERED
            }
        }
    }

    fun saveUserName(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserNameUseCase(userName)
        }
    }
}

sealed class HomeViewState {
    data object LOADING : HomeViewState()
    data object UNREGISTERED : HomeViewState()
    data object REGISTERED : HomeViewState()
}