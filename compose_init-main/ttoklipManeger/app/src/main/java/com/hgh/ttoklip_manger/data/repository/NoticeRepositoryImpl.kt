package com.hgh.ttoklip_manger.data.repository

import com.hgh.ttoklip_manger.data.dto.ApiResponseBody
import com.hgh.ttoklip_manger.data.dto.MessageDto
import com.hgh.ttoklip_manger.data.dto.notice.NoticeDetailDto
import com.hgh.ttoklip_manger.data.dto.notice.NoticeResponseDto
import com.hgh.ttoklip_manger.data.source.remote.api.NoticeService
import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.data.utill.apiHandler
import com.hgh.ttoklip_manger.domain.model.MessageModel
import com.hgh.ttoklip_manger.domain.model.notice.NoticeDetail
import com.hgh.ttoklip_manger.domain.model.notice.NoticeRequest
import com.hgh.ttoklip_manger.domain.model.notice.NoticeResponse
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val api: NoticeService
) : NoticeRepository {
    override suspend fun getNoticePage(page: Int): NetworkResult<NoticeResponse> {
        return apiHandler({ api.getNoticePage(page) }) { response: ApiResponseBody<NoticeResponseDto> -> response.result.toModel() }
    }

    override suspend fun getNoticeDetail(id: Int): NetworkResult<NoticeDetail> {
        return apiHandler({ api.getNoticeDetail(id) }) { response: ApiResponseBody<NoticeDetailDto> -> response.result.toModel() }
    }

    override suspend fun deleteNotice(id: Int): NetworkResult<MessageModel> {
        return apiHandler({ api.deleteNotice(id) }) { response: ApiResponseBody<MessageDto> -> response.result.toModel() }
    }

    override suspend fun postNoticeDetail(request: NoticeRequest): NetworkResult<MessageModel> {
        return apiHandler({api.postNotice(request.toDto())}) {response: ApiResponseBody<MessageDto> -> response.result.toModel()}
    }
}