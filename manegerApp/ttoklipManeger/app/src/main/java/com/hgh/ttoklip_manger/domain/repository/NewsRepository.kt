package com.hgh.ttoklip_manger.domain.repository

import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.domain.model.news.MainNewsResponse

interface NewsRepository {
    suspend fun getMainNews() : NetworkResult<MainNewsResponse>
}