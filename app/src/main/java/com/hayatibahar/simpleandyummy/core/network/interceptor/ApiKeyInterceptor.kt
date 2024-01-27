package com.hayatibahar.simpleandyummy.core.network.interceptor

import com.hayatibahar.simpleandyummy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "apiKey"

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url
            .newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()

        val requestBuilder = request.newBuilder()
            .url(url)

        return chain.proceed(requestBuilder.build())
    }
}