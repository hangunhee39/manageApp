package com.hgh.ttoklip_manger.presentation.ui.main.news

import android.app.Activity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.ttoklip_manger.presentation.base.LoadState

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    navigateToCreateNewsScreen: (Int) -> Unit,
    showBottomSheet: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity
    val snackBarHostState = remember { SnackbarHostState() } //스낵바


    when (viewState.loadState) {
        LoadState.LOADING -> {

        }

        LoadState.ERROR -> {

        }

        LoadState.SUCCESS -> {

        }
    }
}