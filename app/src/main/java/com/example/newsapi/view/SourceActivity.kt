package com.example.newsapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.databinding.ActivitySourceBinding
import com.example.newsapi.view.adapter.SourceAdapter
import com.example.newsapi.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var sourceViewModel: SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceAdapter = SourceAdapter(ArrayList())
        binding.rvSource.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSource.adapter = sourceAdapter

        val bundle = intent.extras
        val getCategory = bundle?.getString("name", "") ?: ""

        sourceViewModel = ViewModelProvider(this).get(SourceViewModel::class.java)
        sourceViewModel.callApiSource(getCategory)

        sourceViewModel.getDataSource().observe(this, Observer { list ->
            list?.let {
                sourceAdapter.listSource(it)
            }
        })

        sourceAdapter.onClick = { source ->
            val bundle = Bundle().apply {
                putString("source", source.name)
            }
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}
