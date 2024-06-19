package com.hgh.ttoklip_manger.data.source.remote.api

import com.hgh.ttoklip_manger.data.dto.ApiResponseBody
import com.hgh.ttoklip_manger.data.dto.news.MainNewsResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("/api/v1/newsletters/posts")
    suspend fun getNewsMainApi(): Response<ApiResponseBody<MainNewsResponseDto>>


}