package com.hgh.ttoklip_manger.presentation.ui.write

import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class WriteContract {

    data class WriteViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val title : String = "",
        val content : String = "",
        val isOk : Boolean = false,
    ): ViewState

    sealed class WriteSideEffect :ViewSideEffect {
        object MoveBack : WriteSideEffect()
        data class ToastMessage(val str: String) : WriteSideEffect()
    }

    sealed class WriteEvent : ViewEvent {
        object OnClickCloseBtn : WriteEvent()
        data class FillInTitle(val title : String) : WriteEvent()
        data class FillInContent(val content : String) : WriteEvent()
        object OnClickWriteBtn : WriteEvent()

    }
}