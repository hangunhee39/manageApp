package com.hgh.ttoklip_manger.domain.usecase

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgh.ttoklip_manger.data.utill.NetworkResult
import com.hgh.ttoklip_manger.domain.model.notice.Notice
import com.hgh.ttoklip_manger.domain.repository.NoticeRepository

class NoticePagingSource(
    private val repository: NoticeRepository
) : PagingSource<Int, Notice>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Notice> {
        Log.d("페이징","NetworkResult.호출은했니??!")
        return try {
            val nextPage = params.key ?: 0
            when (val result = repository.getNoticePage(nextPage)) {
                is NetworkResult.Success -> {
                    Log.d("페이징","NetworkResult.성공")
                    val noticeResponse = result.data
                    LoadResult.Page(
                        data = noticeResponse.notices,
                        prevKey = if (nextPage == 0) null else nextPage - 1,
                        nextKey = if (noticeResponse.notices.isEmpty()) null else nextPage + 1
                    )
                }

                is NetworkResult.Fail -> {
                    Log.d("페이징","NetworkResult.Fail")
                    LoadResult.Error(Exception("Fail: ${result.message}"))
                }

                is NetworkResult.Error -> {
                    Log.d("페이징","NetworkResult.에러2")
                    LoadResult.Error(result.exception)
                }
            }
        } catch (e: Exception) {
            Log.d("페이징","NetworkResult.에러")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Notice>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
