package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.model

data class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source? = Source(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)