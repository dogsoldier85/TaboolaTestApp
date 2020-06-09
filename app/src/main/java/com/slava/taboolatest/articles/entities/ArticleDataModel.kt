package com.slava.taboolatest.articles.entities

import com.slava.taboolatest.persistence.ArticleDBEntity

enum class DataType {
    ARTICLE, TABOOLA_FEED, TABOOLA_WIDGET
}

open class BaseDataModel(val type: DataType)

data class ArticleDataModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
) : BaseDataModel(DataType.ARTICLE) {

    companion object {
        fun create(articleDBEntity: ArticleDBEntity): ArticleDataModel {
            return ArticleDataModel(
                articleDBEntity.id,
                articleDBEntity.name,
                articleDBEntity.description,
                articleDBEntity.thumbnail
            )
        }
    }
}