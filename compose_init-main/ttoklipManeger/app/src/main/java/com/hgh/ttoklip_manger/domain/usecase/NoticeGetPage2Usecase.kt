package com.hgh.ttoklip_manger.domain.usecase

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hgh.ttoklip_manger.domain.model.notice.Notice
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoticeGetPage2Usecase @Inject constructor(
    private val repository: NoticeRepository
) {
    operator fun invoke() : Flow<PagingData<Notice>> {
        Log.d("페이징","NetworkResult.호출은 했니?")
        return Pager(
            config = PagingConfig(pageSize = 4, initialLoadSize = 4, enablePlaceholders = true),
            initialKey = 0,
            pagingSourceFactory = { NoticePagingSource(repository) },
        ).flow
    }
}