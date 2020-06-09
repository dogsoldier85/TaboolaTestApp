package com.slava.taboolatest

import com.slava.taboolatest.network.INetworkHandler
import com.slava.taboolatest.network.entities.ArticleServerModel
import com.slava.taboolatest.entities.Result

class MockNetworkHandler(private val result: Result<List<ArticleServerModel>>) : INetworkHandler {
    override fun getData(): Result<List<ArticleServerModel>> {
        return result
    }

}