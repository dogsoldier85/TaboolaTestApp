package com.slava.taboolatest.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.slava.taboolatest.R
import com.slava.taboolatest.articles.entities.ArticleDataModel
import com.slava.taboolatest.articles.entities.BaseDataModel
import com.slava.taboolatest.articles.entities.DataType

class ArticlesAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val data = ArrayList<BaseDataModel>()

    enum class ArticleViewType(val id: Int) {
        ARTICLE(0), TABOOLA_FEED(1), TABOOLA_WIDGET(2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView: View = when (viewType) {
            ArticleViewType.TABOOLA_FEED.id -> LayoutInflater.from(parent.context)
                .inflate(R.layout.taboola_widget_feed_view, parent, false)
            ArticleViewType.TABOOLA_WIDGET.id -> LayoutInflater.from(parent.context)
                .inflate(R.layout.taboola_widget_view, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item_view, parent, false)
        }

        return when (viewType) {
            ArticleViewType.TABOOLA_FEED.id -> TaboolaFeedViewHolder(itemView)
            ArticleViewType.TABOOLA_WIDGET.id -> TaboolaWidgetViewHolder(itemView)
            else -> ArticleViewHolder(itemView)
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            DataType.ARTICLE -> ArticleViewType.ARTICLE.id
            DataType.TABOOLA_FEED -> ArticleViewType.TABOOLA_FEED.id
            DataType.TABOOLA_WIDGET -> ArticleViewType.TABOOLA_WIDGET.id
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ArticleViewType.ARTICLE.id -> (holder as ArticleViewHolder).bind(data[position] as ArticleDataModel)
            ArticleViewType.TABOOLA_FEED.id -> (holder as TaboolaFeedViewHolder).bind()
            ArticleViewType.TABOOLA_WIDGET.id -> (holder as TaboolaWidgetViewHolder).bind()
        }
    }

    fun loadItems(newData: List<BaseDataModel>) {
        val diffCallback =
            ArticlesDiffUtilCallBack(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}