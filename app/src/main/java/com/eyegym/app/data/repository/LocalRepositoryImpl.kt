package com.eyegym.app.data.repository

import com.eyegym.app.data.local.FavoriteEntity
import com.eyegym.app.data.local.FavoritesDataBase
import com.eyegym.app.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalRepositoryImpl(
    db: FavoritesDataBase
) : LocalRepository {
    private val dao = db.favoriteDao

    override suspend fun insertFavorite(id: Int) {
        dao.insertFavorite(FavoriteEntity(id))
    }

    override suspend fun deleteFavorite(id: Int) {
        dao.deleteFavorite(id)
    }

    override fun getAllFavorites(): Flow<List<Int>> {
        return dao.getAllFavoritesIds().map { favorites ->
            favorites.map { it.id }
        }
    }
}