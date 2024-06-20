package com.hgh.ttoklip_manger.presentation.ui.main

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.presentation.theme.BackgroundColor1
import com.hgh.ttoklip_manger.presentation.theme.Gray500
import com.hgh.ttoklip_manger.presentation.theme.Gray900
import com.hgh.ttoklip_manger.presentation.theme.Pretendard
import kotlinx.coroutines.launch


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TtoklipScreen(
    viewModel: TtoklipViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val viewState by viewModel.viewState.collectAsState()
    var bottomBarState by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    val systemUiController = rememberSystemUiController()
    var statusBarColor: Color by remember { mutableStateOf(White) }


    Scaffold(
        bottomBar = {
            if (bottomBarState) {
                TtoklipBottomNavigation(
                    currentDestination = currentDestination,
                    navigateToScreen = { navigationItem ->
                        navigateBottomNavigationScreen(
                            navController = navController,
                            navigationItem = navigationItem,
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            if (bottomBarState) {
                CreateFAB()
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->

        BackHandler(enabled = sheetState.isVisible) {
            coroutineScope.launch { sheetState.hide() }
        }

        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = TtoklipScreenRoute.NEWS.route
        ) {
            composable(route = TtoklipScreenRoute.NEWS.route) {
                Text(text = "뉴스")
            }

            composable(route = TtoklipScreenRoute.NOTICE.route) {
                Text(text = "알림")
            }
        }

    }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { coroutineScope.launch { sheetState.hide() } },
            sheetState = sheetState,
        ) {

        }
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = true
        )

        systemUiController.setNavigationBarColor(
            color = BackgroundColor1
        )
    }
}

fun navigateBottomNavigationScreen(
    navController: NavHostController,
    navigationItem: BottomNavigationItem,
) {

    navController.navigate(navigationItem.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun CreateFAB(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .offset(y = (50f).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(30.dp)),
            onClick = {},
        ) {
            Box(
                modifier = modifier
                    .background(Black)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo_),
                    contentDescription = null,
                    modifier = modifier,
                    tint = Color.Unspecified,
                )
            }
        }
    }

}

@Composable
fun TtoklipBottomNavigation(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigationItem) -> Unit,
) {
    NavigationBar(
        containerColor = White,
        modifier = Modifier
            .wrapContentHeight(),
    ) {
        BottomNavigationItem.entries.forEach { navigationItem ->
            NavigationBarItem(
                modifier = Modifier,
                icon = {
                    Icon(
                        modifier = Modifier,
                        imageVector = ImageVector.vectorResource(id = navigationItem.icon),
                        contentDescription = null,
                        tint = when (navigationItem.route) {
                            currentDestination?.route -> Gray900
                            else -> Gray500
                        },
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navigationItem.title),
                        color = when (navigationItem.route) {
                            currentDestination?.route -> Gray900
                            else -> Gray500
                        },
                        style = TextStyle(
                            fontFamily = Pretendard,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            lineHeight = 12.sp
                        )
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                onClick = { navigateToScreen(navigationItem) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Gray900,
                    unselectedIconColor = Gray500,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

enum class BottomNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    NEWS(
        route = TtoklipScreenRoute.NEWS.route,
        icon = R.drawable.ic_news_icon_24,
        title = R.string.news
    ),
    NOTICE(
        route = TtoklipScreenRoute.NOTICE.route,
        icon = R.drawable.ic_notice_icon_24,
        title = R.string.notice
    ),
}

enum class TtoklipScreenRoute(val route: String) {
    NEWS("news"),
    NOTICE("notice"),
}

const val KEY_ID = "post-id"