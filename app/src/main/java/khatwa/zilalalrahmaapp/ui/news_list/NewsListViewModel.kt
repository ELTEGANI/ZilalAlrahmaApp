package khatwa.zilalalrahmaapp.ui.news_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import khatwa.zilalalrahmaapp.data.model.NewsItem
import khatwa.zilalalrahmaapp.data.repositories.NewsListRepository
import kotlinx.coroutines.flow.Flow

class NewsListViewModel @ViewModelInject constructor(private val repository: NewsListRepository): ViewModel() {

    fun getNewsList() : Flow<PagingData<NewsItem>> {

        return repository.getNewsList()
                .cachedIn(viewModelScope)

    }
}