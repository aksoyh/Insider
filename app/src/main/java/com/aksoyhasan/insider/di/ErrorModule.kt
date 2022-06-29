package com.aksoyhasan.insider.di

import com.aksoyhasan.insider.error.mapper.ErrorMapper
import com.aksoyhasan.insider.error.mapper.ErrorMapperSource
import com.aksoyhasan.insider.useCase.errors.ErrorManager
import com.aksoyhasan.insider.useCase.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
