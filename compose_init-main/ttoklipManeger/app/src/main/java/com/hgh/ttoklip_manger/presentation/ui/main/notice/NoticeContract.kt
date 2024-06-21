package com.hgh.ttoklip_manger.presentation.ui.main.notice

import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class NoticeContract {

    data class NoticeViewState(
        val loadState: LoadState = LoadState.LOADING
    ): ViewState

    sealed class NoticeSideEffect :ViewSideEffect {

    }

    sealed class NoticeEvent : ViewEvent {
        object InitNoticeScreen : NoticeEvent()
    }
}