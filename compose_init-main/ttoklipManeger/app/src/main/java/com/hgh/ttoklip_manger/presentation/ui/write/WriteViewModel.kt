package com.hgh.ttoklip_manger.presentation.ui.write

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.ttoklip_manger.data.utill.onError
import com.hgh.ttoklip_manger.data.utill.onFail
import com.hgh.ttoklip_manger.data.utill.onSuccess
import com.hgh.ttoklip_manger.domain.model.notice.NoticeRequest
import com.hgh.ttoklip_manger.domain.usecase.NoticePostUsecase
import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val noticePostUsecase: NoticePostUsecase
) : BaseViewModel<WriteContract.WriteViewState, WriteContract.WriteSideEffect, WriteContract.WriteEvent>(
    WriteContract.WriteViewState()
) {
    override fun handleEvents(event: WriteContract.WriteEvent) {
        when (event) {
            WriteContract.WriteEvent.OnClickCloseBtn -> {
                sendEffect({ WriteContract.WriteSideEffect.MoveBack })
            }

            is WriteContract.WriteEvent.FillInContent -> {
                reflectUpdatedText(content = event.content)
            }

            is WriteContract.WriteEvent.FillInTitle -> {
                reflectUpdatedText(title = event.title)
            }

            WriteContract.WriteEvent.OnClickWriteBtn -> {
                postNotice()
            }
        }
    }

    private fun postNotice() = viewModelScope.launch {
        try {
            noticePostUsecase(
                NoticeRequest(
                    title = viewState.value.title,
                    content = viewState.value.content
                )
            ).collect { result ->
                result.onSuccess {
                    sendEffect({ WriteContract.WriteSideEffect.ToastMessage("공지를 생성했습니다.") })
                    sendEffect({ WriteContract.WriteSideEffect.MoveBack })
                }.onFail {
                    sendEffect({ WriteContract.WriteSideEffect.ToastMessage("서버와 통신을 실패했습니다.") })
                    updateState {
                        copy(
                            title = "",
                            content = "",
                            isOk = false
                        )
                    }
                }.onError {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외처리", "$e")
        }
    }

    private fun reflectUpdatedText(
        title: String = viewState.value.title,
        content: String = viewState.value.content
    ) {
        updateState {
            copy(
                title = title,
                content = content,
                isOk = isOkClick()
            )
        }
    }

    private fun isOkClick() =
        run { viewState.value.title.isNotEmpty() && viewState.value.title.isNotEmpty() }
}