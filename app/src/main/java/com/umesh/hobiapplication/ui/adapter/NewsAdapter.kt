package com.umesh.hobiapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.NewsListItemBinding
import com.umesh.hobiapplication.localdb.modeldata.news.everything.Article
import com.umesh.hobiapplication.utils.setImageByGlide

class NewsAdapter(var list: List<Article>, val onClickPost: OnClickPost) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    lateinit var binding: NewsListItemBinding


    inner class NewsViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
           var url=article.urlToImage
            if(url.isNullOrEmpty()){
                url=""
            }
            setImageByGlide(binding.root.context,url,binding.ivImage)
            binding.clPost.setOnClickListener {
                onClickPost.openNewsPost()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item,
            parent,
            false
        )
        return NewsViewHolder()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnClickPost {
        fun openNewsPost()
    }
    }