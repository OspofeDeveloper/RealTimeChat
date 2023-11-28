package com.example.realtimechat.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechat.domain.usecases.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    fun saveUserName(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserNameUseCase(userName)
        }
    }

}