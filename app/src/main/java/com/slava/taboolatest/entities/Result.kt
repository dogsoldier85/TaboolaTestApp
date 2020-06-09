package com.slava.taboolatest.entities

data class Result<T>(val data: T? = null, val exception: Exception? = null)