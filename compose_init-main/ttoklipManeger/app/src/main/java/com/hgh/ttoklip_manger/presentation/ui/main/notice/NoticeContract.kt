package com.hgh.ttoklip_manger.presentation.ui.main.notice

import com.hgh.ttoklip_manger.domain.model.notice.Notice
import com.hgh.ttoklip_manger.domain.model.notice.NoticeDetail
import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class NoticeContract {

    data class NoticeViewState(
        val loadState: LoadState = LoadState.LOADING,
        val notices : List<Notice> = listOf(),
        val noticeDetail : NoticeDetail = NoticeDetail()
    ): ViewState

    sealed class NoticeSideEffect :ViewSideEffect {
        object NavigateToCreateScreen : NoticeSideEffect()
        data class ShowBottomSheet(val id : Int) : NoticeSideEffect()
    }

    sealed class NoticeEvent : ViewEvent {
        object InitNoticeScreen : NoticeEvent()
        object OnClickCreateButton : NoticeEvent()
        data class OnClickNotice(val id : Int) : NoticeEvent()

    }
}