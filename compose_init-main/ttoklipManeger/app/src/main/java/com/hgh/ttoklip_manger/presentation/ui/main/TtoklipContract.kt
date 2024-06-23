package com.hgh.ttoklip_manger.presentation.ui.main

import com.hgh.ttoklip_manger.presentation.base.ViewEvent
import com.hgh.ttoklip_manger.presentation.base.ViewSideEffect
import com.hgh.ttoklip_manger.presentation.base.ViewState

class TtoklipContract {

    object TtoklipViewState: ViewState

    sealed class TtoklipSideEffect :ViewSideEffect {
        object RefreshScreen : TtoklipSideEffect()
    }

    sealed class TtoklipEvent : ViewEvent {
        object FinishedWriteActivity : TtoklipEvent()
    }
}