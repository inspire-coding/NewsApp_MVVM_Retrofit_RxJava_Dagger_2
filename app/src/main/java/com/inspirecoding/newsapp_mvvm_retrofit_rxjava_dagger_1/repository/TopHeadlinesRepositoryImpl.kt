package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.repository

import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model.TopHeadlines
import io.reactivex.Flowable
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor (
    private val topHeadlinesEndpoint: TopHeadlinesEndpoint
) : TopHeadlinesRepository {

    override fun getTopHeadlines(country: String, apiKey: String): Flowable<TopHeadlines> = topHeadlinesEndpoint.getTopHeadlines(country, apiKey)

    override fun getUserSearchInput(apiKey: String, q: String): Flowable<TopHeadlines> = topHeadlinesEndpoint.getUserSearchInput(apiKey, q)
}