package com.example.realtimechat.domain

interface DataRepository {

    suspend fun saveUserName(nickName: String)
}