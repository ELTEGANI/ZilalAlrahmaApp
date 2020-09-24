package khatwa.zilalalrahmaapp.ui.news_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import khatwa.zilalalrahmaapp.databinding.FragmentNewsListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private lateinit var adapter: NewsListAdapter
    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        initAdapter()
        getNews()

        binding.retryButton.setOnClickListener { adapter.retry() }

        return binding.root
    }

    private fun getNews() {
        lifecycleScope.launch {
            viewModel.getNewsList().collectLatest {
                adapter.submitData(it)
            }
        }

    }

    private fun initAdapter() {
        adapter = NewsListAdapter { _, newsId -> showNewsDetails(newsId) }
        binding.recyclerViewNewsList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { adapter.retry() },
                footer = NewsLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.recyclerViewNewsList.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBarLoading.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                        requireContext(),
                        "\uD83D\uDE28 ${it.error}",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showNewsDetails(newsId: Int) {

        findNavController().navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(newsId))
    }
}