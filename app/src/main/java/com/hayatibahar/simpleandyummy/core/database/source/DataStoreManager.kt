package com.hayatibahar.simpleandyummy.core.database.source

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hayatibahar.simpleandyummy.core.common.DataStoreConstants.IS_DARK_MODE
import com.hayatibahar.simpleandyummy.core.common.DataStoreConstants.LAST_REQUEST_TIME
import com.hayatibahar.simpleandyummy.core.common.DataStoreConstants.RECIPE_DATA_STORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreManager @Inject constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = RECIPE_DATA_STORE)

    companion object {
        val lastRequestTime = longPreferencesKey(LAST_REQUEST_TIME)
        val isDarkMode = booleanPreferencesKey(IS_DARK_MODE)
    }

    suspend fun saveLastRequestTime(time: Long) {
        context.dataStore.edit {
            it[lastRequestTime] = time
        }
    }

    fun getLastRequestTime(): Flow<Long> = context.dataStore.data.map {
        it[lastRequestTime] ?: 0
    }

    suspend fun saveDarkModeState(isEnabled: Boolean) {
        context.dataStore.edit {
            it[isDarkMode] = isEnabled
        }
    }

    fun getDarkModeState(): Flow<Boolean> = context.dataStore.data.map {
        it[isDarkMode] ?: false
    }

}