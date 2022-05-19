package com.mhdthasneemp.check_24.data.products.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavItem::class], version = 1)
abstract class CheckDb : RoomDatabase() {
    abstract fun favDao(): FavDao
}