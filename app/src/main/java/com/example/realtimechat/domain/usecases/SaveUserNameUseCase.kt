package com.example.realtimechat.domain.usecases

import com.example.realtimechat.domain.DataRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val dataRepository: DataRepository
){

    suspend operator fun invoke(userName: String) {
        dataRepository.saveUserName(userName)
    }
}