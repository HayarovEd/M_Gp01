package com.eyegym.app.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertFavorite(id: Int)
    suspend fun deleteFavorite(id: Int)
    fun getAllFavorites(): Flow<List<Int>>
}