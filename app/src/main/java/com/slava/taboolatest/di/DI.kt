package com.slava.taboolatest.di

import com.slava.taboolatest.extentions.appContext
import com.slava.taboolatest.network.INetworkHandler
import com.slava.taboolatest.network.NetworkHandler
import com.slava.taboolatest.articles.viewmodel.MainArticlesScreenViewModel
import com.slava.taboolatest.persistence.ArticlesDatabase
import com.slava.taboolatest.repositories.IArticlesRepository
import com.slava.taboolatest.repositories.ArticlesRepository
import com.slava.taboolatest.taboola.ITaboolaMediaAssembler
import com.slava.taboolatest.taboola.TaboolaMediaAssembler
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single<INetworkHandler> { NetworkHandler() }
    single { ArticlesDatabase.getInstance(appContext).newsDao() }
    single<IArticlesRepository> { ArticlesRepository(get(), get()) }
    factory<ITaboolaMediaAssembler> { TaboolaMediaAssembler() }
    viewModel { MainArticlesScreenViewModel(get(), get()) }
}
