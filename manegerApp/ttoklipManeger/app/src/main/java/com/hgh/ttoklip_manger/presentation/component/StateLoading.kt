package com.hgh.ttoklip_manger.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hgh.ttoklip_manger.presentation.theme.MainPurple900

@Composable
fun StateLoading(
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MainPurple900
        )
    }
}

@Composable
@Preview
fun preViewPlanzLoading(){
    StateLoading()
}