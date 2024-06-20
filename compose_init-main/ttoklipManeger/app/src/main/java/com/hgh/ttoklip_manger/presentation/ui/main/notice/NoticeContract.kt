package com.hgh.ttoklip_manger.presentation.ui.main.notice

import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class NoticeContract {

    object NoticeViewState: ViewState

    sealed class NoticeSideEffect :ViewSideEffect {

    }

    sealed class NoticeEvent : ViewEvent {

    }
}