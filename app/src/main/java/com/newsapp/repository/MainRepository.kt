package com.newsapp.repository

import com.newsapp.data.APIClient

class MainRepository(private val apiService: APIClient.ServiceApi) {

    suspend fun getNewsData(newsUrl: String?) = apiService.getNewsData(rssUrl = newsUrl)

}