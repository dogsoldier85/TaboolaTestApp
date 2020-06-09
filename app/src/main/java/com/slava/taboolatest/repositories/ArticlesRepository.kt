package com.slava.taboolatest.repositories

import com.slava.taboolatest.entities.Result
import com.slava.taboolatest.network.INetworkHandler
import com.slava.taboolatest.persistence.ArticleDBEntity
import com.slava.taboolatest.persistence.ArticlesDao
import io.reactivex.Observable
import timber.log.Timber

class ArticlesRepository(
    private val networkHandler: INetworkHandler,
    private val articlesDao: ArticlesDao
) :
    IArticlesRepository {

    override fun getData(): Observable<Result<List<ArticleDBEntity>>> {
        return Observable.create { emitter ->
            emitter.onNext(Result(articlesDao.loadAllArticles()))
            val news = networkHandler.getData()
            if (news.exception == null) {
                Timber.d("Clearing all ArticleDB")
                articlesDao.deleteAllArticles()
                Timber.d("Saving all articles from remote to DB")
                news.data?.forEach { item ->
                    Timber.d("Inserting article item to DB $item")
                    articlesDao.insertArticleItem(
                        ArticleDBEntity(
                            name = item.name ?: "",
                            description = item.description ?: "",
                            thumbnail = item.thumbnail ?: ""
                        )
                    )
                }
            }
            emitter.onNext(Result(articlesDao.loadAllArticles()))
        }
    }
}
