package khatwa.zilalalrahmaapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import khatwa.zilalalrahmaapp.data.ApiInterface
import khatwa.zilalalrahmaapp.data.NewsListPagingSource
import khatwa.zilalalrahmaapp.data.model.NewsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsListRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getNewsList(): Flow<PagingData<NewsItem>> {
        return Pager(
                config = PagingConfig(
                        pageSize = NETWORK_PAGE_SIZE,
                        enablePlaceholders = false
                ),
                pagingSourceFactory = { NewsListPagingSource(apiInterface) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}