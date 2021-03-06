package com.lydiasama.survey.core.network

import com.lydiasama.survey.core.services.token.AccessTokenDataSource
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val accessTokenDataSource: AccessTokenDataSource) :
        Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = accessTokenDataSource.get()
        return if (token.isNotEmpty()) {
            chain.proceed(chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build())
        } else {
            chain.proceed(chain.request())
        }
    }
}