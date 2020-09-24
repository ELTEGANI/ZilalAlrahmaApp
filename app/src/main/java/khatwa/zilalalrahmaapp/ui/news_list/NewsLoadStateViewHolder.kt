package khatwa.zilalalrahmaapp.ui.news_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import khatwa.zilalalrahmaapp.R
import khatwa.zilalalrahmaapp.databinding.NewsLoadStateFooterViewItemBinding

class NewsLoadStateViewHolder(
        private val binding: NewsLoadStateFooterViewItemBinding,
        retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): NewsLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_load_state_footer_view_item, parent, false)
            val binding = NewsLoadStateFooterViewItemBinding.bind(view)
            return NewsLoadStateViewHolder(binding, retry)
        }
    }
}