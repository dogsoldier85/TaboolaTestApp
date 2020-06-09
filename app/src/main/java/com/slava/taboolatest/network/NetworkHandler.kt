package com.slava.taboolatest.network

import com.slava.taboolatest.entities.Result
import com.slava.taboolatest.BuildConfig
import com.slava.taboolatest.R
import com.slava.taboolatest.extentions.appContext
import com.slava.taboolatest.network.entities.ArticleServerModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkHandler : INetworkHandler {
    private var retrofitService: TaboolaApiServices

    init {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
        retrofitService = retrofitBuilder.build().create(TaboolaApiServices::class.java)
    }

    override fun getData(): Result<List<ArticleServerModel>> {
        Timber.d("Fetching articles from remote API")
        val response = retrofitService.getData().execute()
        return if (response.isSuccessful) {
            Timber.d("Fetching articles from remote API - Succeeded ${response.errorBody()}")
            Result(data = response.body())
        } else {
            Timber.d("Fetching articles from remote API - Failed ${response.errorBody()}")
            Result(exception = IOException(appContext.getString(R.string.general_error)))
        }
    }
}