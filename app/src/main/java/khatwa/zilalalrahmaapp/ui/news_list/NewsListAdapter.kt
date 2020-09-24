package khatwa.zilalalrahmaapp.ui.news_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import khatwa.zilalalrahmaapp.data.model.NewsItem
import khatwa.zilalalrahmaapp.databinding.NewsItemBinding

class NewsListAdapter(private val onClickListener: (View, Int) -> Unit) : PagingDataAdapter<NewsItem, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            (holder as NewsViewHolder).bind(news)

            holder.itemView.setOnClickListener { view ->
                onClickListener.invoke(view, news.id)
            }
        }
    }

    class NewsViewHolder private constructor(private val binding: NewsItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsItem) {
            binding.apply {
                news = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(layoutInflater, parent, false)

                return NewsViewHolder(binding)
            }
        }
    }

    private class NewsDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }
    }


}