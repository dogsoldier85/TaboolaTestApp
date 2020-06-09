package com.slava.taboolatest.articles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.slava.taboolatest.articles.decoration.ItemVerticalMarginsDecoration
import com.slava.taboolatest.articles.adapter.ArticlesAdapter
import com.slava.taboolatest.articles.viewmodel.MainArticlesScreenViewModel
import com.slava.taboolatest.R
import org.koin.android.ext.android.inject


class MainArticlesScreenFragment : Fragment() {

    private var newsAdapter = ArticlesAdapter()
    private lateinit var errorMessage: TextView
    private val viewModel: MainArticlesScreenViewModel by inject()

    companion object {
        fun newInstance() =
            MainArticlesScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_articles_screen_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchArticles()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsList(view)
        errorMessage = view.findViewById(R.id.error_label)
        initViewModel()
    }

    private fun initNewsList(view: View) {
        val newsList = view.findViewById<RecyclerView>(R.id.news_list)
        newsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        (newsList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        newsList.addItemDecoration(ItemVerticalMarginsDecoration())
        newsList.adapter = newsAdapter
        newsList.overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun initViewModel() {
        viewModel.newsLiveData()
            .observe(viewLifecycleOwner, Observer { items ->
                newsAdapter.loadItems(items)
                errorMessage.text = ""
            })
        viewModel.errorHandlingLiveData()
            .observe(viewLifecycleOwner, Observer {
                errorMessage.text = it
                errorMessage.visibility = View.VISIBLE
            })
    }
}
