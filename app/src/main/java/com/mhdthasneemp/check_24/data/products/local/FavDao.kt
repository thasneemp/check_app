package com.mhdthasneemp.check_24.data.products.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(favItem: FavItem): Long

    @Query("SELECT isFav FROM FavItem WHERE uid =:favId")
    suspend fun isFavored(favId: Int): Int?
}