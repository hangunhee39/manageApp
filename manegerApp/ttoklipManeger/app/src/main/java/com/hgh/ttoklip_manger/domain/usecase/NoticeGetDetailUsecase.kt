package com.hgh.ttoklip_manger.domain.usecase

import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticeGetDetailUsecase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(id: Int) = flow {
        emit(repository.getNoticeDetail(id))
    }
}