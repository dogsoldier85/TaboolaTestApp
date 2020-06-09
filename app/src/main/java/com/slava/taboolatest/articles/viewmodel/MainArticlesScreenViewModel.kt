package com.slava.taboolatest.articles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.slava.taboolatest.R
import com.slava.taboolatest.articles.entities.ArticleDataModel
import com.slava.taboolatest.articles.entities.BaseDataModel
import com.slava.taboolatest.common.BaseViewModel
import com.slava.taboolatest.extentions.appContext
import com.slava.taboolatest.repositories.IArticlesRepository
import com.slava.taboolatest.taboola.ITaboolaMediaAssembler
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainArticlesScreenViewModel(
    private val articlesRepository: IArticlesRepository,
    private val taboolaMediaAssembler: ITaboolaMediaAssembler
) :
    BaseViewModel() {

    private val articleLiveData: MutableLiveData<List<BaseDataModel>> = MutableLiveData()
    private val errorHandling: MutableLiveData<String> = MutableLiveData()

    fun fetchArticles() {
        articlesRepository.getData()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val uiData = ArrayList<BaseDataModel>()
                it.data?.forEach { item ->
                    uiData.add(ArticleDataModel.create(item))
                } ?: Timber.d("No data received from server")
                taboolaMediaAssembler.addTaboolaWidgets(uiData)
                articleLiveData.postValue(uiData)
            }, {
                it?.let { errorHandling.postValue(it.message) }
                    ?: errorHandling.postValue(appContext.getString(R.string.no_data_to_show))
            }).untilCleared()
    }

    fun newsLiveData(): LiveData<List<BaseDataModel>> {
        return articleLiveData
    }

    fun errorHandlingLiveData(): LiveData<String> {
        return errorHandling
    }
}
