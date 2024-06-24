package com.hgh.ttoklip_manger.presentation.ui.main.news

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.domain.model.news.News
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
                    ManageTabRow(
                        tabIndex = pagerState.currentPage
                    ) { index ->
                        coroutineScope.launch { pagerState.animateScrollToPage(page = index) }
                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize(),
                    ) { pageIndex ->
                        when (pageIndex) {
                            NewsTapMenu.HOUSE_WORK.ordinal -> {
                                if (viewState.safeNews.isEmpty())
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(R.string.empty_news),
                                            style = Typography.labelLarge,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                else NewsList(news = viewState.homeNews) {

                                }
                            }

                            NewsTapMenu.RECIPE.ordinal -> {
                                if (viewState.recipeNews.isEmpty())
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(R.string.empty_news),
                                            style = Typography.labelLarge,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                else NewsList(news = viewState.recipeNews) {

                                }

                            }

                            NewsTapMenu.SAFE_LIFE.ordinal -> {
                                if (viewState.safeNews.isEmpty())
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(R.string.empty_news),
                                            style = Typography.labelLarge,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                else NewsList(news = viewState.safeNews) {

                                }
                            }

                            NewsTapMenu.WELFARE.ordinal -> {
                                if (viewState.welfareNews.isEmpty())
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(R.string.empty_news),
                                            style = Typography.labelLarge,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                else NewsList(news = viewState.welfareNews) {

                                }
                            }
                        }
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


@Composable
fun NewsList(
    news: List<News>,
    onItemClick: (News) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(news.size) { index ->
            NewsItem(
                news = news[index],
                onItemClick = onItemClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun NewsItem(
    news: News,
    onItemClick: (News) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemClick(news) },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Gray200),
        colors = CardColors(
            contentColor = Color.Black,
            disabledContentColor = Color.Black,
            containerColor = Color.White,
            disabledContainerColor = Color.White
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // 이미지 로딩
                AsyncImage(
                    model = news.mainImageUrl,
                    contentDescription = "News Image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    error = painterResource(id = R.drawable.ic_logo_)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = news.title,
                    style = Typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }


            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    NewsItem(
        News(
            title = "안녕하세요",
            writtenTime = "오늘",
            mainImageUrl = "https://ddoklipbk.s3.ap- northeast-2.amazonaws.com/photo/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-06-24%20%EC%98%A4%ED%9B%84%202.53.20.png",
            newsletterId = 0
        )
    ) {

    }
}

enum class NewsTapMenu(@StringRes val textId: Int) {
    HOUSE_WORK(R.string.house_work),
    RECIPE(R.string.recipe),
    SAFE_LIFE(R.string.safe_life),
    WELFARE(R.string.welfare)
}