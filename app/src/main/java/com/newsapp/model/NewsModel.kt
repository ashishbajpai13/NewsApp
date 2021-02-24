package com.newsapp.model

import java.io.Serializable

data class NewsModel(
    val status: String?,
    val feed: Feed,
    val items: List<Items>
) {
    class Feed(
        val url: String?,
        val title: String?,
        val link: String?,
        val author: String?,
        val description: String?,
        val image: String?
    )

    class Items(
        val title: String?,
        val pubDate: String?,
        val link: String?,
        val guid: String?,
        val author: String?,
        val thumbnail: String?,
        val description: String?,
        val content: String?,
        val enclosure: Enclosure?,
        val categories: Array<String>?
    ): Serializable {
        class Enclosure(
            val link: String?,
            val type: String?,
            val thumbnail: String?
        ): Serializable
    }
}