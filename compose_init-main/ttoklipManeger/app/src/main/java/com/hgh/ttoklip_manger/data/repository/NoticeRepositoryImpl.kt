package com.hgh.ttoklip_manger.data.repository

import com.hgh.ttoklip_manger.data.dto.ApiResponseBody
import com.hgh.ttoklip_manger.data.dto.news.MainNewsResponseDto
import com.hgh.ttoklip_manger.data.dto.notice.NoticeResponseDto
import com.hgh.ttoklip_manger.data.source.remote.api.NoticeService
import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.data.utill.apiHandler
import com.hgh.ttoklip_manger.domain.model.news.MainNewsResponse
import com.hgh.ttoklip_manger.domain.model.notice.NoticeResponse
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val api: NoticeService
) : NoticeRepository {
    override suspend fun getNoticePage(page: Int): NetworkResult<NoticeResponse> {
        return apiHandler({ api.getNoticePage(page) }) { response: ApiResponseBody<NoticeResponseDto> -> response.result.toModel() }
    }
}