package khatwa.zilalalrahmaapp.data

import androidx.paging.PagingSource
import khatwa.zilalalrahmaapp.data.model.NewsItem
import retrofit2.HttpException
import java.io.IOException

private const val NEWS_STARTING_PAGE_INDEX = 1

class NewsListPagingSource(
        private val apiInterface: ApiInterface
) : PagingSource<Int, NewsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        val position = params.key ?: NEWS_STARTING_PAGE_INDEX
        return try {
            val response = apiInterface.getNewsList(position, params.loadSize)
            val results = response.news
            LoadResult.Page(
                    data = results,
                    prevKey = if (position == NEWS_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}