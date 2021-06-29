package com.example.elmnewsapp.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elmnewsapp.R
import com.example.elmnewsapp.data.model.News
import com.example.elmnewsapp.databinding.NewsListItemBinding


class NewsViewHolder (
    private val binding : NewsListItemBinding,
    private var onClickCallback : ((item : News) -> Unit) = {})
    : RecyclerView.ViewHolder(binding.root) {


    fun bind(news : News){
        itemView.setOnClickListener {
            onClickCallback (news)
        }
        binding.model = news
        val descriptionLength = news.description?.length ?: 0
        binding.shortDescription = news.description?.substring ( 0, descriptionLength.coerceAtMost(
            MAX_DESCRIPTION_LENGTH
        ) )
        Glide
            .with(binding.root.context)
            .load(news.image)
            .placeholder(R.drawable.image_placeholder)
            .error(R.color.black)
            .into(binding.newsThumbnail)
        binding.executePendingBindings()
    }

    companion object {
        private const val MAX_DESCRIPTION_LENGTH = 100
    }
}