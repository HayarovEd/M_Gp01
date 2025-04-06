package com.eyegym.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        FavoriteEntity::class],
    version = 1
)
abstract class FavoritesDataBase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}