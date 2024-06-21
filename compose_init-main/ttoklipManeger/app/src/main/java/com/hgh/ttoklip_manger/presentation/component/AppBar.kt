package com.hgh.ttoklip_manger.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.presentation.component.type.AppBarMenu
import com.hgh.ttoklip_manger.presentation.theme.Gray900
import com.hgh.ttoklip_manger.presentation.theme.PlanzTypography

@Composable
fun CreateAppBar(
    modifier: Modifier = Modifier,
    title: String,
    logoId: Int? = null,
    onCreateClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        title = title,
        back = null,
        logoId = logoId,
        menu = AppBarMenu.CREATE,
        onMenuClick = onCreateClick,
        onBackClick = { },
    )
}

@Composable
fun BackAppBar(
    modifier: Modifier = Modifier,
    title: String,
    logoId: Int? = null,
    onExitClick: () -> Unit,
) {
    MainAppBar(
        modifier = modifier,
        title = title,
        logoId = logoId,
        menu = AppBarMenu.BACK,
        onMenuClick = onExitClick,
        onBackClick = { },
    )
}

@Composable
private fun MainAppBar(
    modifier: Modifier = Modifier,
    logoId: Int? = null,
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
        if (logoId != null)
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp),
                imageVector = ImageVector.vectorResource(id = logoId),
                contentDescription = null
            )

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = PlanzTypography.bodyLarge,
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


@Preview(showBackground = true)
@Composable
fun PlanzExitAppBarPreview() {
    CreateAppBar(
        title = "뉴스 관리",
        logoId = R.drawable.ic_logo_,
        onCreateClick = {}
    )
}