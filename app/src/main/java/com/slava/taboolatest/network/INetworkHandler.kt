package com.slava.taboolatest.network

import com.slava.taboolatest.entities.Result
import com.slava.taboolatest.network.entities.ArticleServerModel

interface INetworkHandler {
    fun getData(): Result<List<ArticleServerModel>>
}