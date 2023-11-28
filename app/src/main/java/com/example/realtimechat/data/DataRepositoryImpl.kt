package com.example.realtimechat.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.realtimechat.domain.DataRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataRepository {

    companion object {
        private val USER_NAME = stringPreferencesKey("username")
    }

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user"
    )

    override suspend fun saveUserName(nickName: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[USER_NAME] = nickName
        }
    }

}