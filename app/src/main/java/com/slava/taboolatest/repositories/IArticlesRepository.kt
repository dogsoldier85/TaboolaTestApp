package com.slava.taboolatest.repositories

import com.slava.taboolatest.entities.Result
import com.slava.taboolatest.persistence.ArticleDBEntity
import io.reactivex.Observable

interface IArticlesRepository {
    fun getData(): Observable<Result<List<ArticleDBEntity>>>
}