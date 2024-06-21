package com.hgh.ttoklip_manger.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.ttoklip_manger.R
import com.hgh.ttoklip_manger.presentation.theme.Gray500
import com.hgh.ttoklip_manger.presentation.theme.MainPurple900
import com.hgh.ttoklip_manger.presentation.theme.PlanzTypography


@Composable
fun StateError(
    modifier: Modifier = Modifier.fillMaxSize(),
    retryVisible: Boolean = false,
    onClickRetry: () -> Unit = { },
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_logo_),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.error_tex),
                color = Gray500,
                style = PlanzTypography.bodyMedium,
            )

            if (retryVisible) {
                Spacer(modifier = Modifier.height(28.dp))
                Button(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(30.dp),
                    border = BorderStroke(width = 1.dp, color = MainPurple900),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MainPurple900),
                    onClick = { onClickRetry() },
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    elevation = null,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_create),
                            contentDescription = null,
                        )

                        Text(
                            text = stringResource(R.string.retry_button_title),
                            style = PlanzTypography.labelSmall,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPlanError() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        StateError(retryVisible = true, onClickRetry = { })
    }
}
