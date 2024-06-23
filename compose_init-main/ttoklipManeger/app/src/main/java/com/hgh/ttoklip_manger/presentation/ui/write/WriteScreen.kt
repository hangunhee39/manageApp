package com.hgh.ttoklip_manger.presentation.ui.write

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.presentation.component.BackAppBar
import com.hgh.ttoklip_manger.presentation.component.BasicButton
import com.hgh.ttoklip_manger.presentation.theme.Gray300
import com.hgh.ttoklip_manger.presentation.theme.MainPurple900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteScreen(
    viewModel: WriteViewModel = hiltViewModel(),
    onClose: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Scaffold(
        topBar = {
            BackAppBar(
                title = stringResource(id = R.string.write_notice),
            ) {
                viewModel.setEvent(WriteContract.WriteEvent.OnClickCloseBtn)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = viewState.title,
                    onValueChange = { viewModel.setEvent(WriteContract.WriteEvent.FillInTitle(it)) },
                    label = { Text("제목") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                    )
                )
                OutlinedTextField(
                    value = viewState.content,
                    onValueChange = { viewModel.setEvent(WriteContract.WriteEvent.FillInContent(it)) },
                    label = { Text("내용") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(400.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                text = "작성완료",
                enabled = viewState.isOk,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 16.dp, end = 16.dp),
                textColor = Color.Black,
            ) {
                viewModel.setEvent(WriteContract.WriteEvent.OnClickWriteBtn)
            }
        }

    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                WriteContract.WriteSideEffect.MoveBack -> {
                    onClose()
                }

                is WriteContract.WriteSideEffect.ToastMessage -> {
                    Toast.makeText(context, effect.str, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Preview
@Composable
fun pr() {
    WriteScreen {

    }
}