package khatwa.zilalalrahmaapp.data.model

data class NewsItem(
        var id: Int = 0,
        var title: String? = null,
        var details: String? = null,
        var newsDate: String? = null,
        var imagePath: String? = null
)