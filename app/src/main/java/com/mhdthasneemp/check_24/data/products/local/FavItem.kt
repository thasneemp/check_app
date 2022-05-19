package com.mhdthasneemp.check_24.data.products.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavItem(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "isFav") val isFav: Int?,
)
