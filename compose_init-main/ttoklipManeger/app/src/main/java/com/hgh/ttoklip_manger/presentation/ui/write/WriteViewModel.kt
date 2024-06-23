package com.hgh.ttoklip_manger.presentation.ui.write

import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(

) : BaseViewModel<WriteContract.WriteViewState, WriteContract.WriteSideEffect, WriteContract.WriteEvent>(
    WriteContract.WriteViewState()
) {
    override fun handleEvents(event: WriteContract.WriteEvent) {
        when(event){
           WriteContract.WriteEvent.OnClickCloseBtn -> {
               sendEffect({ WriteContract.WriteSideEffect.MoveBack })
           }
        }
    }
}