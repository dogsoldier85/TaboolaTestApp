package com.slava.taboolatest.articles.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.slava.taboolatest.R
import com.slava.taboolatest.extentions.appContext

class ItemVerticalMarginsDecoration : RecyclerView.ItemDecoration() {

    private val padding =
        appContext.resources.getDimensionPixelSize(R.dimen.new_item_vertical_margins)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.top = padding
        }
        outRect.bottom = padding
    }
}