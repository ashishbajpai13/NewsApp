package com.newsapp.ui.activity.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.newsapp.repository.MainRepository
import com.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val mainRepository: MainRepository) : ViewModel(){

  fun getNewsData(newsUrl: String?) = liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
          emit(Resource.success(data = mainRepository.getNewsData(newsUrl = newsUrl)))
      } catch (exception: Exception) {
          emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
      }
  }

}