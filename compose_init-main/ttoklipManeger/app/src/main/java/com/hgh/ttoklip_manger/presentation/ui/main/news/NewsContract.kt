package com.hgh.ttoklip_manger.presentation.ui.main.news

import com.hgh.ttoklip_manger.domain.model.news.News
import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class NewsContract {

    data class NewsViewState(
        val loadState: LoadState = LoadState.LOADING,
        val homeNews: List<News> = listOf(),
        val recipeNews: List<News> = listOf(),
        val safeNews: List<News> = listOf(),
        val welfareNews: List<News> = listOf(),
    ): ViewState

    sealed class NewsSideEffect :ViewSideEffect {}

    sealed class NewsEvent : ViewEvent {
        object InitNewsScreen : NewsEvent()
    }
}