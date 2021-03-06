package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository

import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.TopHeadlines
import io.reactivex.Observable

interface TopHeadlinesRepository {

    fun getTopHeadlines (
        country : String,
        apiKey : String
    ) : Observable<TopHeadlines>

    fun getUserSearchInput (
        apiKey: String,
        q: String
    ) : Observable<TopHeadlines>

}