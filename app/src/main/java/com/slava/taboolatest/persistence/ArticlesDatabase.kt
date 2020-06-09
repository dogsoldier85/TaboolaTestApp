package com.slava.taboolatest.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleDBEntity::class], version = 1)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun newsDao(): ArticlesDao

    companion object {

        @Volatile
        private var INSTANCE: ArticlesDatabase? = null

        fun getInstance(context: Context): ArticlesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                    context.applicationContext,
                    ArticlesDatabase::class.java, "ArticlesDB.db"
                )
                .build()
    }
}


