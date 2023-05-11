package com.example.newsapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.databinding.ItemArticleBinding
import com.example.newsapi.model.article.Article

class ArticleAdapter(var listArticle : List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    var onClick : ((Article)->Unit)? = null

    class ViewHolder ( var binding : ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleArticle.text = listArticle[position].title
        holder.binding.DateArticle.text = listArticle[position].publishedAt
        Glide.with(holder.itemView).load(listArticle[position].urlToImage).into(holder.binding.imageArticle)

        holder.binding.itemArticle.setOnClickListener {
            onClick?.invoke(listArticle[position])
        }
    }


    override fun getItemCount(): Int {
        return listArticle.size
    }

    fun setDataArticle(list : List<Article>){
        listArticle = list
        notifyDataSetChanged()
    }
}