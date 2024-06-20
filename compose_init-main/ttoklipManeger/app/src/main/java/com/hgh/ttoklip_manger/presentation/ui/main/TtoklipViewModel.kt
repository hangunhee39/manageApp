package com.hgh.ttoklip_manger.presentation.ui.main

import com.hgh.ttoklip_manger.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TtoklipViewModel @Inject constructor(

) : BaseViewModel<TtoklipContract.TtoklipViewState, TtoklipContract.TtoklipSideEffect, TtoklipContract.TtoklipEvent, >(
    TtoklipContract.TtoklipViewState
) {
    override fun handleEvents(event: TtoklipContract.TtoklipEvent) {
        TODO("Not yet implemented")
    }
}