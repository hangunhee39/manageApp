package com.hgh.ttoklip_manger.domain.usecase

import com.hgh.ttoklip_manger.domain.model.notice.NoticeRequest
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticePostUsecase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(noticeRequest: NoticeRequest) = flow {
        emit(repository.postNoticeDetail(noticeRequest))
    }
}