package com.newsapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsapp.data.APIClient
import com.newsapp.repository.MainRepository
import com.newsapp.ui.activity.news.NewsViewModel


class ViewModelFactory(private val serviceApi: APIClient.ServiceApi): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                return NewsViewModel(MainRepository(serviceApi)) as T
            }

            else -> throw IllegalArgumentException("Unknown Class")
        }
    }
}