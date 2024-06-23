package com.hgh.ttoklip_manger.presentation.ui.main.news

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.presentation.base.LoadState
import com.hgh.ttoklip_manger.presentation.component.CreateAppBar
import com.hgh.ttoklip_manger.presentation.component.StateError
import com.hgh.ttoklip_manger.presentation.component.StateLoading
import com.hgh.ttoklip_manger.presentation.theme.Gray200
import com.hgh.ttoklip_manger.presentation.theme.Gray300
import com.hgh.ttoklip_manger.presentation.theme.Gray900
import com.hgh.ttoklip_manger.presentation.theme.Typography
import com.hgh.ttoklip_manger.presentation.ui.main.TtoklipViewModel
import com.hgh.ttoklip_manger.presentation.utill.composableActivityViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    mainViewModel: TtoklipViewModel = composableActivityViewModel(),
    viewModel: NewsViewModel = hiltViewModel(),
    navigateToCreateNewsScreen: (Int) -> Unit,
    showBottomSheet: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity
    val snackBarHostState = remember { SnackbarHostState() } //스낵바
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { NewsTapMenu.entries.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(NewsContract.NewsEvent.InitNewsScreen)
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
                        title = stringResource(id = R.string.news_manager),
                        logoId = R.drawable.ic_logo_,
                        onCreateClick = { }
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    ManageTabRow(tabIndex = pagerState.currentPage
                    ) { index ->
                        coroutineScope.launch { pagerState.animateScrollToPage(page = index) }
                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize(),
                    ) {

                    }
                }
            }
        }
    }
}


@Composable
fun ManageTabRow(
    tabIndex: Int,
    onTabClick: (Int) -> Unit,
) {
    TabRow(
        selectedTabIndex = tabIndex,
        contentColor = Color.Transparent,
        indicator = @Composable { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[tabIndex])
                    .padding(horizontal = 36.dp),
                color = Gray900
            )
        },
        divider = @Composable { Divider(color = Gray200) }
    ) {
        NewsTapMenu.entries.forEachIndexed { index, menu ->
            Tab(
                selected = tabIndex == index,
                onClick = { onTabClick(index) },
                text = {
                    Text(
                        maxLines = 1,
                        text = stringResource(id = menu.textId),
                        style = Typography.labelMedium,
                        color = Color.Unspecified,

                        )
                },
                selectedContentColor = Gray900,
                unselectedContentColor = Gray300
            )
        }
    }
}

enum class NewsTapMenu(@StringRes val textId: Int) {
    HOUSE_WORK(R.string.house_work),
    RECIPE(R.string.recipe),
    SAFE_LIFE(R.string.safe_life),
    WELFARE(R.string.welfare)
}