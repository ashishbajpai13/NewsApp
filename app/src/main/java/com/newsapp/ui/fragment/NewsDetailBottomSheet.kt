package com.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.newsapp.databinding.FragmentNewsDetailBottomSheetBinding
import com.newsapp.model.NewsModel
import com.newsapp.utils.TimeUtils
import java.io.Serializable


class NewsDetailBottomSheet : BottomSheetDialogFragment() {

    private var newsData: NewsModel.Items? = null
    private var _binding: FragmentNewsDetailBottomSheetBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsData = it.getSerializable(NEWSDATA) as NewsModel.Items
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBottomSheetBinding.inflate(inflater, container, false)
        init()
        return _binding?.root
    }

    private fun init() {
        binding?.let { binding ->
            newsData?.let {
                binding.title.text = it.title
                binding.postedOn.text = "Posted ${TimeUtils().timeAgo(it.pubDate)}"
                Glide.with(requireContext()).load(it.enclosure?.link).into(binding.bannerImage)
                it.description?.let {
                    binding.description.text = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
                }
            }
        }
    }

    companion object {
        private const val NEWSDATA = "newsdata"
        @JvmStatic
        fun newInstance(items: NewsModel.Items) =
            NewsDetailBottomSheet().apply {
                arguments = Bundle().apply {
                    putSerializable(NEWSDATA, items as Serializable)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}