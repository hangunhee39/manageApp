package com.hgh.ttoklip_manger.domain.repository

import com.hgh.ttoklip_manger.data.dto.ApiResponseBody
import com.hgh.ttoklip_manger.data.dto.news.MainNewsResponseDto
import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.domain.model.news.MainNewsResponse
import com.hgh.ttoklip_manger.domain.model.notice.NoticeResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeRepository {
    suspend fun getNoticePage(page : Int): NetworkResult<NoticeResponse>
}