package com.example.realtimechat.domain

import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun saveUserName(nickName: String)

    /** Esta función no es suspend porque es un Flow directamente */
    fun getUserName(): Flow<String>

    suspend fun clearUserName()
}