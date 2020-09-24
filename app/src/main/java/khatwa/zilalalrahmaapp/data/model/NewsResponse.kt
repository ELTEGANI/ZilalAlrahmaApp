package khatwa.zilalalrahmaapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
        @SerializedName("page")
        var page: Int = 0,

        @SerializedName("news")
        var news: List<NewsItem>,

        @SerializedName("total_results")
        var totalResults: Int = 0,

        @SerializedName("total_pages")
        var totalPages: Int = 0
)