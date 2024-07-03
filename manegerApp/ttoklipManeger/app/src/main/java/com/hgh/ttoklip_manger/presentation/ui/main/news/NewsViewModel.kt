package com.hgh.ttoklip_manger.presentation.ui.main.news

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.ttoklip_manger.data.utill.onError
import com.hgh.ttoklip_manger.data.utill.onFail
import com.hgh.ttoklip_manger.data.utill.onSuccess
import com.hgh.ttoklip_manger.domain.usecase.NewsGetMainUsecase
import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import com.hgh.ttoklip_manger.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsGetMainUsecase: NewsGetMainUsecase
) : BaseViewModel<NewsContract.NewsViewState, NewsContract.NewsSideEffect, NewsContract.NewsEvent>(
    NewsContract.NewsViewState()
) {
    override fun handleEvents(event: NewsContract.NewsEvent) {
        when (event) {
            is NewsContract.NewsEvent.InitNewsScreen -> {
                // 뉴스 메인 호출
                getMainNew()
            }
        }
    }

    private fun getMainNew() = viewModelScope.launch {
        newsGetMainUsecase().collect { result ->
            try {
                result.onSuccess {
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            homeNews = it.categoryResponses.houseWork,
                            recipeNews = it.categoryResponses.recipe,
                            safeNews = it.categoryResponses.safeLiving,
                            welfareNews = it.categoryResponses.welfarePolicy
                        )
                    }
                }.onFail {
                    updateState { copy(loadState = LoadState.ERROR) }
                }.onError {
                    updateState { copy(loadState = LoadState.ERROR) }
                    throw it
                }
            } catch (e: Exception) {
                Log.d("예외처리", "$e")
            }

        }
    }
}