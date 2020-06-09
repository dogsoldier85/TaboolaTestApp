package com.slava.taboolatest.articles.adapter

import android.view.View
import com.slava.taboolatest.R
import com.taboola.android.TaboolaWidget

class TaboolaWidgetViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private val taboolaWidget = itemView.findViewById<TaboolaWidget>(R.id.taboola_view)

    fun bind() {
        taboolaWidget.fetchContent()
    }

}