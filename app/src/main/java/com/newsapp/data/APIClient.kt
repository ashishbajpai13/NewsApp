package com.newsapp.data

import com.newsapp.Config
import com.newsapp.model.NewsModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIClient {

    @JvmField
    val ApiInterface: ServiceApi
    private val retrofit: Retrofit = Retrofit.Builder()
       .baseUrl(Config.BASE_URL)
       .addConverterFactory(GsonConverterFactory.create())
       .client(OkHttpClient.Builder().build())
       .build()

    init {
        ApiInterface = retrofit.create(
            ServiceApi::class.java
        )
    }

    interface ServiceApi {
        @GET("v1/api.json")
        suspend fun getNewsData(@Query("rss_url") rssUrl: String?): NewsModel
    }
}