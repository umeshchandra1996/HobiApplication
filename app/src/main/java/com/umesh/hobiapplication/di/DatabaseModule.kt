package com.umesh.hobiapplication.di

import android.app.Application
import androidx.room.Room
import com.umesh.hobiapplication.localdb.DbConstant
import com.umesh.hobiapplication.localdb.AppDatabase
import com.umesh.hobiapplication.localdb.dao.ProductDao
import com.umesh.hobiapplication.localdb.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//created database for app using hilt

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        DbConstant.DB_NAME
    ).allowMainThreadQueries().build()

    @Provides
    internal fun provideUserDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.userDao()
    }

    @Provides
    internal fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }


}



