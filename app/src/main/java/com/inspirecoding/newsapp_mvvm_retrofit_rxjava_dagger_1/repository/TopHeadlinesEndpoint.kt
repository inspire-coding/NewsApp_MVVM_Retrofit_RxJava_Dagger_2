package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository

import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.TopHeadlines
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesEndpoint {

    @GET ("top-headlines")
    fun getTopHeadlines (
        @Query("country") country : String,
        @Query("apiKey") apiKey : String
    ) : Flowable<TopHeadlines>

    @GET("top-headlines")
    fun getUserSearchInput (
        @Query("apiKey") apiKey: String,
        @Query("q") q: String
    ) : Flowable<TopHeadlines>

}