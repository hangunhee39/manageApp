package com.hgh.ttoklip_manger.presentation.ui.main.notice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.domain.model.notice.Notice
import com.hgh.ttoklip_manger.domain.model.notice.NoticeDetail
import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.component.BasicButton
import com.hgh.ttoklip_manger.presentation.component.CreateAppBar
import com.hgh.ttoklip_manger.presentation.component.StateError
import com.hgh.ttoklip_manger.presentation.component.StateLoading
import com.hgh.ttoklip_manger.presentation.theme.Gray200
import com.hgh.ttoklip_manger.presentation.theme.Gray500
import com.hgh.ttoklip_manger.presentation.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticeScreen(
    viewModel: NoticeViewModel = hiltViewModel(),
    navigateToNoticeWriteScreen: () -> Unit,
    showSheet: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()


    val notices = viewModel.notices.collectAsLazyPagingItems()

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
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                ) {
                    if (notices.itemCount == 0)
                        Text(text = "공지가 없습니다.", modifier = Modifier.align(Alignment.Center))
                    else
                        NoticesList(
                            notices
                        ) { notices ->
                            viewModel.setEvent(NoticeContract.NoticeEvent.OnClickNotice(notices.noticeId))
                        }
                }
            }

            if (sheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = { coroutineScope.launch { sheetState.hide() } },
                    sheetState = sheetState,
                ) {
                    NoticeBottomSheet(viewState.noticeDetail) {

                    }
                }
            }
        }
    }
    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                NoticeContract.NoticeSideEffect.NavigateToCreateScreen -> {
                }

                is NoticeContract.NoticeSideEffect.ShowBottomSheet -> {
                    coroutineScope.launch { sheetState.show() }
                }
            }
        }
    }

}

@Composable
fun NoticesList(
    notices: LazyPagingItems<Notice>,
    onItemClick: (Notice) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(notices.itemCount) { index ->
            val notice = notices[index]
            notice?.let {
                NoticeItem(
                    notice = it,
                    onItemClick = onItemClick
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun NoticeItem(
    notice: Notice,
    onItemClick: (Notice) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemClick(notice) },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Gray200),
        colors = CardColors(
            contentColor = Black,
            disabledContentColor = Black,
            containerColor = White,
            disabledContainerColor = White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = notice.title, style = Typography.labelLarge, maxLines = 2)

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun NoticeBottomSheet(
    noticeDetail: NoticeDetail,
    onDelete: (Int) -> Unit,
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(color = Color.White)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = noticeDetail.title,
                    style = Typography.headlineSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_exit_24),
                    tint = Color.Unspecified,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .align(Alignment.Top)
                        .clickable { },

                    )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticeDetail.createdAt,
                style = Typography.labelSmall,
                color = Gray500,
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = noticeDetail.content,
                style = Typography.bodySmall,
                color = Black,
            )
            Spacer(modifier = Modifier.height(32.dp))

            BasicButton(
                text = "삭제하기",
                modifier = Modifier
                    .fillMaxWidth(),
                buttonColor = Color.Red
            ) {

            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(color = Color.White)
        )
    }
}


@Preview
@Composable
fun NoticeItemsPreview() {
    //NoticeItem(notice = Notice("dddd", 12, "(필독) 공지사항"), {})
    //NoticeBottomSheet()
}