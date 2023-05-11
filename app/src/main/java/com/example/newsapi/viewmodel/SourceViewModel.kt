package com.example.newsapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi.model.ResponseDataSource
import com.example.newsapi.model.Source
import com.example.newsapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourceViewModel : ViewModel(){

    var liveDataSource : MutableLiveData<List<Source>?> = MutableLiveData()

    fun getDataSource(): MutableLiveData<List<Source>?> {
        return  liveDataSource
    }

    fun callApiSource(category : String){
        ApiClient.instance.getAllSources(category)
            .enqueue(object : Callback<ResponseDataSource> {
                override fun onResponse(
                    call: Call<ResponseDataSource>,
                    response: Response<ResponseDataSource>
                ) {
                    if (response.isSuccessful){
                        liveDataSource.postValue(response.body()!!.sources)
                    }else{
                        liveDataSource.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                    liveDataSource.postValue(null)
                }


            })
    }


}