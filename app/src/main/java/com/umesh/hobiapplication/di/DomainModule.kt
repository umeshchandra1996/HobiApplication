package com.umesh.hobiapplication.di

import com.umesh.hobiapplication.repository.EverythingNewsRepository
import com.umesh.hobiapplication.repository.EverythingNewsRepositoryImpl
import com.umesh.hobiapplication.repository.ProductRepository
import com.umesh.hobiapplication.repository.UserRepository
import com.umesh.hobiapplication.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideUsersUseCase(userRepository: UserRepository) =
        UsersUseCaseImpl(userRepository) as UsersUseCase

    @Provides
    @Singleton
    fun provideProductUseCase(productRepository: ProductRepository) =
        ProductUserCaseImpl(productRepository) as ProductUseCase

    @Provides
    @Singleton
    fun provideEverythingNewsUseCase(everythingNewsRepository: EverythingNewsRepository) =
        EverythingNewsUseCaseImpl(everythingNewsRepository) as EverythingNewsUseCase

}