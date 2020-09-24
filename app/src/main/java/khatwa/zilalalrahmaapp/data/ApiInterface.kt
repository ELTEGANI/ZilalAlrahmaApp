package khatwa.zilalalrahmaapp.data

import khatwa.zilalalrahmaapp.data.model.NewsItem
import khatwa.zilalalrahmaapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("NewsDetails.php")
    suspend fun getNewsDetails(@Query("newsId") newsID: Int): NewsItem

    @GET("NewsList.php")
    suspend fun getNewsList(@Query("page") PageNo: Int,
                            @Query("per_page") perPage: Int): NewsResponse
}