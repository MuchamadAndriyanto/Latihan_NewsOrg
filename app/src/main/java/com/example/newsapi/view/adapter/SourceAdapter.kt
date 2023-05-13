package com.example.newsapi.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.databinding.ItemSourceBinding
import com.example.newsapi.model.source.Source

class SourceAdapter(var listSource : List<Source>): RecyclerView.Adapter<SourceAdapter.ViewHolder>()  {
    var onClickso : ((Source)->Unit)? = null

    class ViewHolder(var binding: ItemSourceBinding):RecyclerView.ViewHolder(binding.root)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameSource.text = listSource[position].name
        holder.binding.nameSource.setOnClickListener {
            onClickso?.invoke(listSource[position])
        }
    }

    override fun getItemCount(): Int {
        return  listSource.size
    }

    fun setDataResouce(list: List<Source>){
        this.listSource = list
        notifyDataSetChanged()

        Log.d("HASIL_SOURCE", list.toString())
    }


}