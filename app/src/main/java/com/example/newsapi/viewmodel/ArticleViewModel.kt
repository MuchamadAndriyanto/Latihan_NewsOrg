package com.example.newsapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.model.article.Article
import com.example.newsapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel: ViewModel() {

    var liveDataArticle : MutableLiveData<List<Article>?> = MutableLiveData()

    fun getDataArticle() : MutableLiveData<List<Article>?>{
        return liveDataArticle
    }

    fun callApiArticle(article : String){
        ApiClient.instance.gellAllArticles(article).enqueue(object : Callback<List<Article>>{
            override fun onResponse(
                call: Call<List<Article>>,
                response: Response<List<Article>>
            ) {

                if (response.isSuccessful){
                    liveDataArticle.postValue(response.body()!!)
                }else{
                    liveDataArticle.postValue(null)
                }


            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {

                liveDataArticle.postValue(null)

            }

        })

    }
}