package com.example.newsapi.network

import com.example.newsapi.model.source.ResponseDataSource
import com.example.newsapi.model.article.Article
import com.example.newsapi.model.article.ResponseDataArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines/sources")
    fun getAllSources(
        @Query("category") category : String,
        @Query("apiKey") apiKey : String = "ad992dbb7a9c4b15bb9b37e5d4ef3358"
    ) : Call<ResponseDataSource>

    @GET("top-headlines")
    fun getAllArticles(
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String = "ad992dbb7a9c4b15bb9b37e5d4ef3358"
    ) : Call<ResponseDataArticle>

}