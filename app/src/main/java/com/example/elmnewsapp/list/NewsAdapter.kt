package com.example.elmnewsapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.elmnewsapp.data.model.News
import com.example.elmnewsapp.databinding.NewsListItemBinding

class NewsAdapter(private val callback : ((news : News) -> Unit))
    : PagingDataAdapter<News, NewsViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(container: ViewGroup, p1: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(container.context)
        val binding = NewsListItemBinding.inflate(inflater, container, false)
        return NewsViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position : Int) {
        val news = getItem(position)
        news?.let {
            holder.bind(news)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldNews: News,
                                         newNews: News) = oldNews.title == newNews.title

            override fun areContentsTheSame(oldNews: News,
                                            newNews: News) = oldNews == newNews
        }
    }
}