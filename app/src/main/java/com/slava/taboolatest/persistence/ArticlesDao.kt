package com.slava.taboolatest.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleItem(articleDBItem: ArticleDBEntity)

    @Query("SELECT * FROM Articles")
    fun loadAllArticles(): List<ArticleDBEntity>

    @Query("DELETE FROM Articles")
    fun deleteAllArticles()
}

