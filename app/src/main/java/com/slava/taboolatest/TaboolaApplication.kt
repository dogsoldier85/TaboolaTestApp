package com.slava.taboolatest

import android.app.Application
import com.slava.taboolatest.di.AppModule
import com.slava.taboolatest.persistence.ArticlesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaboolaApplication : Application() {

    companion object {
        lateinit var instance: TaboolaApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ArticlesDatabase.getInstance(this)
        startKoin {
            androidContext(this@TaboolaApplication)
            modules(AppModule)
        }
    }
}