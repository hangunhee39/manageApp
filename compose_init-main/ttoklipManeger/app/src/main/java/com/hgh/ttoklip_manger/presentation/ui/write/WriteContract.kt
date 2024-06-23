package com.hgh.ttoklip_manger.presentation.ui.write

import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class WriteContract {

    data class WriteViewState(
        val loadState: LoadState = LoadState.LOADING,
    ): ViewState

    sealed class WriteSideEffect :ViewSideEffect {
        object MoveBack : WriteSideEffect()
    }

    sealed class WriteEvent : ViewEvent {
        object OnClickCloseBtn : WriteEvent()
    }
}