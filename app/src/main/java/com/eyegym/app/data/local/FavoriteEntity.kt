package com.eyegym.app.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eyegym.app.domain.utils.TABLE_FAVORITE
import com.eyegym.app.domain.utils.TABLE_FAVORITE_ID

@Entity(tableName = TABLE_FAVORITE)
data class FavoriteEntity (
    @PrimaryKey
    @ColumnInfo(name = TABLE_FAVORITE_ID)
    val id: Int,
)