package com.hgh.ttoklip_manger.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hgh.ttoklip_manger.presentation.component.type.AppBarMenu
import com.hgh.ttoklip_manger.presentation.theme.Gray900
import com.hgh.ttoklip_manger.presentation.theme.PlanzTypography

@Composable
fun CreateAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onCreateClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        title = title,
        back = null,
        menu = AppBarMenu.CREATE,
        onMenuClick = onCreateClick,
        onBackClick = { },
    )
}

@Composable
fun BackAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onExitClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        title = title,
        menu = AppBarMenu.BACK,
        onMenuClick = onExitClick,
        onBackClick = { },
    )
}

@Composable
private fun MainAppBar(
    modifier: Modifier = Modifier,
    title: String,
    back: AppBarMenu? = null,
    menu: AppBarMenu? = null,
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = PlanzTypography.headlineSmall,
            color = Gray900,
            maxLines = 1,
        )

        if (back != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = back.icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = back.contentDescription),
                modifier = Modifier
                    .padding(start = back.horizontalPadding)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onBackClick() }
                    .align(Alignment.CenterStart),
            )
        }
        if (menu != null) {
            Icon(
                imageVector = ImageVector.vectorResource(id = menu.icon),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = menu.contentDescription),
                modifier = Modifier
                    .padding(end = menu.horizontalPadding)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onMenuClick() }
                    .align(Alignment.CenterEnd),
            )
        }
    }
}