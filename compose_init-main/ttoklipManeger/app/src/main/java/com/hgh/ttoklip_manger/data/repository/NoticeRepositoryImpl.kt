package com.hgh.ttoklip_manger.data.repository

import com.hgh.ttoklip_manger.data.source.remote.api.NoticeService
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val api : NoticeService
): NoticeRepository {

}