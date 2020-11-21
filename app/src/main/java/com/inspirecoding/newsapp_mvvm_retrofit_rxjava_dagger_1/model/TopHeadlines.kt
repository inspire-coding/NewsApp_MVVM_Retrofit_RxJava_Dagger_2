package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model

data class TopHeadlines(
    val articles: List<Article>? = listOf(),
    val status: String? = "",
    val totalResults: Int? = 0
)