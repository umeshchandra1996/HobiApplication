package com.umesh.hobiapplication.di

import com.umesh.hobiapplication.localdb.dao.ProductDao
import com.umesh.hobiapplication.localdb.dao.UsersDao
import com.umesh.hobiapplication.network.ApiService
import com.umesh.hobiapplication.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUsersRepository(usersDao: UsersDao,apiService: ApiService) =
        UserRepositoryImpl(usersDao ,apiService) as UserRepository

    @Singleton
    @Provides
    fun provideProductRepository(productDao: ProductDao) =
        ProductRepositoryImpl(productDao) as ProductRepository

    @Singleton
    @Provides
    fun provideEveryThingNewsRepository(usersDao: UsersDao,apiService: ApiService) =
        EverythingNewsRepositoryImpl(usersDao,apiService) as EverythingNewsRepository
}