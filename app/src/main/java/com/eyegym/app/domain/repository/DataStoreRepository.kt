package com.eyegym.app.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setCountStarts(value: Int)
    fun getCountStarts(): Flow<Int>
}