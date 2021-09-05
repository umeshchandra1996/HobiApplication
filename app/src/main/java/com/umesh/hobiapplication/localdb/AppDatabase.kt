package com.umesh.hobiapplication.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umesh.hobiapplication.localdb.dao.ProductDao
import com.umesh.hobiapplication.localdb.dao.UsersDao
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.localdb.modeldata.Users

@Database(entities = [Users::class,Product::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao():UsersDao
    abstract fun productDao(): ProductDao
}