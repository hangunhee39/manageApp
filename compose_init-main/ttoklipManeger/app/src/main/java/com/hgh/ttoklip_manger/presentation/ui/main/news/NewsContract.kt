package com.hgh.ttoklip_manger.presentation.ui.main.news

import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class NewsContract {

    data class NewsViewState(
        val loadState: LoadState = LoadState.LOADING,
    ): ViewState

    sealed class NewsSideEffect :ViewSideEffect {

    }

    sealed class NewsEvent : ViewEvent {
        object InitHomeScreen : NewsEvent()
    }
}