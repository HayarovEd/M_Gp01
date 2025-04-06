package com.eyegym.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eyegym.app.domain.utils.TABLE_FAVORITE
import com.eyegym.app.domain.utils.TABLE_FAVORITE_ID
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    //LocationEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM $TABLE_FAVORITE")
    fun getAllFavoritesIds(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM $TABLE_FAVORITE WHERE $TABLE_FAVORITE_ID =:id")
    suspend fun deleteFavorite(id: Int)

}