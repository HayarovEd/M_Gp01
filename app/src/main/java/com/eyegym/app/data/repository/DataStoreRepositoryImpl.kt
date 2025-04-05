package com.eyegym.app.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.eyegym.app.domain.repository.DataStoreRepository
import com.eyegym.app.domain.utils.COUNT_STARTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    companion object {
        val FIELD_COUNT_STARTS = intPreferencesKey(COUNT_STARTS)
    }

    override suspend fun setCountStarts(value: Int) {
        dataStore.edit { settings ->
            settings[FIELD_COUNT_STARTS] = value
        }
    }

    override fun getCountStarts(): Flow<Int> {
        return dataStore.data.map {
            it[FIELD_COUNT_STARTS] ?: 0
        }
    }
}