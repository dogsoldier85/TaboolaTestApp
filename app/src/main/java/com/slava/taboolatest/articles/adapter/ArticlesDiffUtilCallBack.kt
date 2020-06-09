package com.slava.taboolatest.articles.adapter

import androidx.recyclerview.widget.DiffUtil
import com.slava.taboolatest.articles.entities.BaseDataModel

class ArticlesDiffUtilCallBack(
    private val oldList: List<BaseDataModel>,
    private val newList: List<BaseDataModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].type == newList[newItemPosition].type
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return old == new
    }
}