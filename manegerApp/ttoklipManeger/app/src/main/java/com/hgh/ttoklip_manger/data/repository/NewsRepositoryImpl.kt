package com.hgh.ttoklip_manger.data.repository

import com.hgh.ttoklip_manger.data.dto.ApiResponseBody
import com.hgh.ttoklip_manger.data.dto.news.MainNewsResponseDto
import com.hgh.ttoklip_manger.data.source.remote.api.NewsService
import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.data.utill.apiHandler
import com.hgh.ttoklip_manger.domain.model.news.MainNewsResponse
import com.hgh.ttoklip_manger.domain.repository.NewsRepository
import okhttp3.ResponseBody
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val api: NewsService
): NewsRepository {
    override suspend fun getMainNews(): NetworkResult<MainNewsResponse> {
        return apiHandler({api.getNewsMainApi()}) {response: ApiResponseBody<MainNewsResponseDto> -> response.result.toModel()}
    }

}