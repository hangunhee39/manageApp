package com.hgh.samplecompose.data.utill

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response  {
        try {
            val token = ("Bearer " + "JWT tokwn")
            val originRequest = chain.request().newBuilder()
                .addHeader("Authorization", token)
                .build()


            /** 자동 리플레쉬 + 로그아웃 구현*/


            Log.d(AppConfig.TAG_DEBUG, "HttpRequestInterceptor: ${originRequest.url}")
            return chain.proceed(originRequest)
        } catch (e: Exception) {
            Log.d(AppConfig.TAG_DEBUG, "HttpRequestInterceptor error: ${e.message}")
            throw e
        }
    }

}