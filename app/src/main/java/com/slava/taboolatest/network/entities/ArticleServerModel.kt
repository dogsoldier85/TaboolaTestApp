package com.slava.taboolatest.network.entities

import com.google.gson.annotations.SerializedName

class ArticleServerModel(
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnail") val thumbnail: String?
)