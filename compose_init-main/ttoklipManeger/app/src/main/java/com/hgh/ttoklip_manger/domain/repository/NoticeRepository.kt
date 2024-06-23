package com.hgh.ttoklip_manger.domain.repository

import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.domain.model.MessageModel
import com.hgh.ttoklip_manger.domain.model.notice.NoticeDetail
import com.hgh.ttoklip_manger.domain.model.notice.NoticeRequest
import com.hgh.ttoklip_manger.domain.model.notice.NoticeResponse

interface NoticeRepository {
    suspend fun getNoticePage(page : Int): NetworkResult<NoticeResponse>
    suspend fun getNoticeDetail(id : Int): NetworkResult<NoticeDetail>
    suspend fun deleteNotice(id : Int) : NetworkResult<MessageModel>
    suspend fun postNoticeDetail(request : NoticeRequest): NetworkResult<MessageModel>
}
