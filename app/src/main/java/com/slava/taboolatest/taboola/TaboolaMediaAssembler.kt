package com.slava.taboolatest.taboola

import com.slava.taboolatest.articles.entities.BaseDataModel
import com.slava.taboolatest.articles.entities.DataType
import timber.log.Timber

class TaboolaMediaAssembler : ITaboolaMediaAssembler {

    companion object {
        private const val TABOOLA_WIDGET_POSITION = 2
    }

    override fun addTaboolaWidgets(data: ArrayList<BaseDataModel>) {
        if (data.size > TABOOLA_WIDGET_POSITION) {
            data.add(TABOOLA_WIDGET_POSITION, BaseDataModel(DataType.TABOOLA_WIDGET))
            Timber.d("Successfully add Taboola widget at position ${TABOOLA_WIDGET_POSITION + 1}")
        } else {
            Timber.d("Failed to add Taboola widget at position ${TABOOLA_WIDGET_POSITION + 1}")
        }
        Timber.d("adding Taboola widget feed at the end of the list")
        data.add(BaseDataModel(DataType.TABOOLA_FEED))
    }
}