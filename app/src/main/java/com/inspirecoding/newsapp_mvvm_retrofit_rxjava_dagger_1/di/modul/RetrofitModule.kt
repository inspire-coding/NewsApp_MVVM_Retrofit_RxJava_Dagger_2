package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.modul

import com.google.gson.GsonBuilder
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository.TopHeadlinesEndpoint
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun providesApi (retrofit: Retrofit) : TopHeadlinesEndpoint {
        return retrofit.create(TopHeadlinesEndpoint::class.java)
    }

}