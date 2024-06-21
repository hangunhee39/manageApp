package com.hgh.ttoklip_manger.presentation.ui.main.notice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.component.CreateAppBar
import com.hgh.ttoklip_manger.presentation.component.StateError
import com.hgh.ttoklip_manger.presentation.component.StateLoading

@Composable
fun NoticeScreen(
    viewModel: NoticeViewModel = hiltViewModel(),
    navigateToNoticeWriteScreen: () -> Unit,
    showSheet: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(NoticeContract.NoticeEvent.InitNoticeScreen)
    }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            StateLoading()
        }

        LoadState.ERROR -> {
            StateError()
        }

        LoadState.SUCCESS -> {
            Scaffold(
                topBar = {
                    CreateAppBar(
                        title = stringResource(id = R.string.notice_manager),
                        logoId = R.drawable.ic_logo_
                    ) {

                    }
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding))
            }
        }

    }

}