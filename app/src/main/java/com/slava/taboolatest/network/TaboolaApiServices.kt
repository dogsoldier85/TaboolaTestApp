package com.slava.taboolatest.network

import com.slava.taboolatest.network.entities.ArticleServerModel
import retrofit2.Call
import retrofit2.http.*

interface TaboolaApiServices {

    @GET("taboola-mobile-sdk/public/home_assignment/data.json")
    fun getData(): Call<ArrayList<ArticleServerModel>>
}