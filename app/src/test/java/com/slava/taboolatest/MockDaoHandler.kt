package com.slava.taboolatest

import com.slava.taboolatest.persistence.ArticleDBEntity
import com.slava.taboolatest.persistence.ArticlesDao
import com.slava.taboolatest.entities.Result

class MockDaoHandler(
    private val result: Result<List<ArticleDBEntity>>,
    private val simulate: Boolean = false
) :
    ArticlesDao {
    private val articleDBItem = ArticleDBEntity(
        id = 1,
        name = "From DB",
        description = "description",
        thumbnail = "thumbnail")
    private var isFirstFetchHappened = false

    override fun insertArticleItem(articleDBItem: ArticleDBEntity) {
    }

    override fun loadAllArticles(): List<ArticleDBEntity> {
        if (simulate) {
            return if (isFirstFetchHappened) {
                result.data ?: arrayListOf()
            } else {
                isFirstFetchHappened = true
                arrayListOf()
            }
        }
        return result.data ?: arrayListOf()
    }

    override fun deleteAllArticles() {
    }
}