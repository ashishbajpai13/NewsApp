package com.newsapp.ui.adapter

import android.R
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.databinding.NewsItemLayoutBinding
import com.newsapp.databinding.NewsItemLayoutLargeBinding
import com.newsapp.model.NewsModel
import com.newsapp.ui.fragment.NewsDetailBottomSheet
import com.newsapp.utils.TimeUtils


class NewsAdapter(
    private val newsList: List<NewsModel.Items>,
    private val activity: FragmentActivity
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var binding: NewsItemLayoutBinding
    private lateinit var bindingLargeItem: NewsItemLayoutLargeBinding
    private var lastPosition = -1

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            if (position == 0) {
                Glide.with(itemView.context).load(newsList[position].enclosure?.link)
                    .into(bindingLargeItem.newsImage)
                newsList[position].title?.let {
                    bindingLargeItem.headerText.text = it
                }
                newsList[position].pubDate?.let {
                    setDate(TimeUtils().convertSDF(it), bindingLargeItem.dateTv)
                }
                itemView.setOnClickListener {
                    NewsDetailBottomSheet.newInstance(newsList[position]).show(
                        activity.supportFragmentManager,
                        NewsDetailBottomSheet().tag
                    )
                }
            } else {
                Glide.with(itemView.context).load(newsList[position].thumbnail)
                    .into(binding.newsImage)
                newsList[position].title?.let {
                    binding.headerText.text = it
                }
                newsList[position].pubDate?.let {
                    setDate(TimeUtils().convertSDF(it), binding.dateTv)
                }
                itemView.setOnClickListener {
                    NewsDetailBottomSheet.newInstance(newsList[position]).show(
                        activity.supportFragmentManager,
                        NewsDetailBottomSheet().tag
                    )
                }
            }
        }

        fun setDate(date: String?, textView: TextView) {
            date?.let {
                val sb = SpannableStringBuilder(date)
                val bss = StyleSpan(Typeface.BOLD)
                sb.setSpan(bss, 0, date.length - 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                textView.text = sb
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        bindingLargeItem =
            NewsItemLayoutLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(
            when (viewType) {
                0 -> bindingLargeItem.root
                else -> binding.root
            }
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(position = position)
        setAnimation(holder.itemView, position = position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) return 0 else 1
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}