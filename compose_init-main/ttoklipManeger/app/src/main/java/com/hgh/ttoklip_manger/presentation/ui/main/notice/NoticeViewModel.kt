package com.hgh.ttoklip_manger.presentation.ui.main.notice

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.hgh.ttoklip_manger.data.utill.onError
import com.hgh.ttoklip_manger.data.utill.onFail
import com.hgh.ttoklip_manger.data.utill.onSuccess
import com.hgh.ttoklip_manger.domain.model.notice.Notice
import com.hgh.ttoklip_manger.domain.usecase.NoticeGetDetailUsecase
import com.hgh.ttoklip_manger.domain.usecase.NoticePagingUsecase
import com.hgh.ttoklip_manger.domain.usecase.NoticeGetPageUsecase
import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import com.hgh.ttoklip_manger.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getNoticeGetPageUsecase: NoticeGetPageUsecase,
    private val noticePagingUsecase: NoticePagingUsecase,
    private val noticeDetailUsecase: NoticeGetDetailUsecase,
) : BaseViewModel<NoticeContract.NoticeViewState, NoticeContract.NoticeSideEffect, NoticeContract.NoticeEvent>(
    NoticeContract.NoticeViewState()
) {

    private val _notices = MutableStateFlow<PagingData<Notice>>(PagingData.empty())
    val notices: StateFlow<PagingData<Notice>> = _notices

    init {
        viewModelScope.launch {
            noticePagingUsecase().cachedIn(viewModelScope).collectLatest {
                _notices.value = it
            }
        }
    }

    override fun handleEvents(event: NoticeContract.NoticeEvent) {
        when (event) {
            is NoticeContract.NoticeEvent.InitNoticeScreen -> {
                updateState { copy(loadState = LoadState.SUCCESS) }
                //getWaitingPlans()
                //updateState { copy(loadState = LoadState.SUCCESS) }
            }

            is NoticeContract.NoticeEvent.OnClickCreateButton -> {
                sendEffect({ NoticeContract.NoticeSideEffect.NavigateToCreateScreen })
            }

            is NoticeContract.NoticeEvent.OnClickNotice -> {
                getNoticesDetail(id = event.id)
                sendEffect({ NoticeContract.NoticeSideEffect.ShowBottomSheet(id = event.id) })
            }

            else -> {}
        }
    }

    private fun getNotices() = viewModelScope.launch {
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

    private fun getNoticesDetail(id: Int) = viewModelScope.launch {
        try {
            noticeDetailUsecase(id).collect { result ->
                result.onSuccess {
                    updateState { copy(loadState = LoadState.SUCCESS, noticeDetail = it) }
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

    fun deleteNotice(notice: Notice) {
        _notices.value = _notices.value.filter { it.noticeId != notice.noticeId }
    }

}