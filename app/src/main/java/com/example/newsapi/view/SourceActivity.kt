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

    private lateinit var binding : ActivitySourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var sourceVm : SourceViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceAdapter = SourceAdapter(ArrayList())

        sourceVm = ViewModelProvider(this)[SourceViewModel::class.java]

        binding.rvSource.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val bundle = intent.extras
        val getCategory = bundle?.getString("name", "") ?: ""

        sourceVm.callApiSource(getCategory)
        binding.rvSource.adapter = sourceAdapter

        @Suppress("RedundantSamConstructor")
        sourceVm.getDataSource().observe(this, Observer { list ->
            list?.let {
                sourceAdapter.setDataResouce(it)
            }
        })


        sourceAdapter.onClickso = { source ->
            val bundle = Bundle().apply {
                putString("source", source.name)
            }
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }


    }
}