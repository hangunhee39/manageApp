package com.hgh.ttoklip_manger.presentation.ui.main.notice

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hgh.ttoklip_manger.data.utill.onError
import com.hgh.ttoklip_manger.data.utill.onFail
import com.hgh.ttoklip_manger.data.utill.onSuccess
import com.hgh.ttoklip_manger.domain.model.notice.Notice
import com.hgh.ttoklip_manger.domain.usecase.NoticeGetPage2Usecase
import com.hgh.ttoklip_manger.domain.usecase.NoticeGetPageUsecase
import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import com.hgh.ttoklip_manger.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getNoticeGetPageUsecase: NoticeGetPageUsecase,
    private val noticeGetPage2Usecase: NoticeGetPage2Usecase
) : BaseViewModel<NoticeContract.NoticeViewState, NoticeContract.NoticeSideEffect, NoticeContract.NoticeEvent>(
    NoticeContract.NoticeViewState()
) {
    val notices: Flow<PagingData<Notice>> = noticeGetPage2Usecase().cachedIn(viewModelScope)

    override fun handleEvents(event: NoticeContract.NoticeEvent) {
        when (event) {
            is NoticeContract.NoticeEvent.InitNoticeScreen -> {
                updateState { copy(loadState = LoadState.SUCCESS) }
                //getWaitingPlans()
                //updateState { copy(loadState = LoadState.SUCCESS) }
            }

            else -> {}
        }
    }

    private fun getWaitingPlans() = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        try {
            getNoticeGetPageUsecase(0).collect { result ->
                result.onSuccess {
                    updateState { copy(loadState = LoadState.SUCCESS, notices = it.notices) }
                }.onFail {
                    updateState { copy(loadState = LoadState.ERROR) }
                }.onError {
                    throw it
                }

            }
        } catch (e: Exception) {
            Log.e("예외처리", "$e")
        }
    }
}