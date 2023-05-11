package com.example.newsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val getUrl = bundle?.getString("url", "").toString()


        binding.DetailArticle.apply {
            webViewClient = WebViewClient()
            loadUrl(getUrl.toString())
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
        }
    }




}
