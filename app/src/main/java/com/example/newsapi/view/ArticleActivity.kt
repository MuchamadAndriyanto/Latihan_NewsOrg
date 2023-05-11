package com.example.newsapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityArticleBinding
import com.example.newsapi.view.adapter.ArticleAdapter
import com.example.newsapi.viewmodel.ArticleViewModel

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var artAdapter: ArticleAdapter
    private lateinit var artViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        artAdapter = ArticleAdapter(ArrayList())

        binding.rvArticle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticle.adapter = artAdapter

        val bundle = intent.extras
        val getSource = bundle?.getString("source", "") ?: ""

        artViewModel.callApiArticle(getSource)

        artViewModel.getDataArticle().observe(this, Observer { list ->
            list?.let {
                artAdapter.setDataArticle(it)
            }
        })

        artAdapter.onClick = { article ->
            val bundle = Bundle().apply {
                putString("url", article.url)
            }
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}