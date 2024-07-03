package com.hgh.ttoklip_manger.domain.usecase

import com.hgh.ttoklip_manger.domain.repository.NewsRepository
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsGetMainUsecase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = flow {
        emit(repository.getMainNews())
    }
}