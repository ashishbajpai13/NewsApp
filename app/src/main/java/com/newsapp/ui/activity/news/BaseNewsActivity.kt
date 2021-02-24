package com.newsapp.ui.activity.news

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.newsapp.R
import com.newsapp.data.APIClient
import com.newsapp.databinding.ActivityBaseNewsBinding
import com.newsapp.ui.adapter.NewsAdapter
import com.newsapp.ui.base.BaseActivity
import com.newsapp.ui.base.ViewModelFactory
import com.newsapp.utils.Status

class BaseNewsActivity : BaseActivity() {

    private lateinit var binding: ActivityBaseNewsBinding
    private lateinit var viewModel: NewsViewModel

    companion object {
        private const val RSS_URL = "http://www.abc.net.au/news/feed/51120/rss.xml"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(APIClient.ApiInterface))
            .get(NewsViewModel::class.java)
        getNewsFeed()
        binding.swipeDownRefresh.setOnRefreshListener {
            getNewsFeed()
            binding.swipeDownRefresh.isRefreshing = false
        }
    }

    private fun getNewsFeed() {
        showProgressBar()
        viewModel.getNewsData(RSS_URL).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideProgressBar()
                        it.data?.let {
                            when (it.status) {
                                this.resources.getString(R.string.status_ok) -> {
                                    binding.newsFeedRecycler.adapter =
                                        NewsAdapter(newsList = it.items, this)
                                }
                                else -> {
                                    showToast(this.resources.getString(R.string.general_error_message))
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        showToast(this.resources.getString(R.string.general_error_message))
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
}