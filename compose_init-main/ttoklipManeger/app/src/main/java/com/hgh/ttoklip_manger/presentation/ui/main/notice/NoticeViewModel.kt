package com.hgh.ttoklip_manger.presentation.ui.main.notice

import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import com.hgh.ttoklip_manger.presentation.ui.main.TtoklipContract
import com.hgh.ttoklip_manger.presentation.ui.main.news.NewsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(

) : BaseViewModel<NoticeContract.NoticeViewState, NoticeContract.NoticeSideEffect, NoticeContract.NoticeEvent >(
    NoticeContract.NoticeViewState
) {
    override fun handleEvents(event: NoticeContract.NoticeEvent) {

    }
}