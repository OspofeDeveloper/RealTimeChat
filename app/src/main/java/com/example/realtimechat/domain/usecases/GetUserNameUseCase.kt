package com.example.realtimechat.domain.usecases

import com.example.realtimechat.domain.DataRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {

    /** Desde el repositorio nos llega un Flow asi que tenemos que modelarlo a un String */
    suspend operator fun invoke(): String = dataRepository.getUserName().first()

}