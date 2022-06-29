package com.aksoyhasan.insider.di

import com.aksoyhasan.insider.data.repository.InsiderRepository
import com.aksoyhasan.insider.data.repositorySource.InsiderRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun insiderRepositorySource(dataRepository: InsiderRepository): InsiderRepositorySource
}
