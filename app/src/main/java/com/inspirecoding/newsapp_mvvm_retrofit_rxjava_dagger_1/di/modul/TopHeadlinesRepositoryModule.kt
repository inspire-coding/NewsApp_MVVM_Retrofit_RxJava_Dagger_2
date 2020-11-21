package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.modul

import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository.TopHeadlinesRepository
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository.TopHeadlinesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class TopHeadlinesRepositoryModule {

    @Binds
    abstract fun providesRepository (
        topHeadlinesRepositoryImpl: TopHeadlinesRepositoryImpl
    ) : TopHeadlinesRepository

}