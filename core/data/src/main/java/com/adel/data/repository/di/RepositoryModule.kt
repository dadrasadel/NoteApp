package com.adel.data.repository.di

import com.adel.data.repository.Repository
import com.adel.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * this class is used for provide all repositories that used by use-cases in our Application
 * in this class we provide Repository
 * @see Repository
 * @see RepositoryImpl for implementation
 */


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl):Repository=repositoryImpl
}