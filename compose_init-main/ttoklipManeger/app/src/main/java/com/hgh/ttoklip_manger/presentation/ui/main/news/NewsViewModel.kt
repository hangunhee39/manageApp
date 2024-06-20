package com.hgh.ttoklip_manger.presentation.ui.main.news

import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import com.hgh.ttoklip_manger.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

) : BaseViewModel<NewsContract.NewsViewState, NewsContract.NewsSideEffect, NewsContract.NewsEvent >(
    NewsContract.NewsViewState()
) {
    override fun handleEvents(event: NewsContract.NewsEvent) {
        when (event) {
            is NewsContract.NewsEvent.InitHomeScreen -> {
                updateState { NewsContract.NewsViewState(LoadState.LOADING) }
                // 뉴스 메인 호출
            }
            else -> {

            }
        }
    }
}