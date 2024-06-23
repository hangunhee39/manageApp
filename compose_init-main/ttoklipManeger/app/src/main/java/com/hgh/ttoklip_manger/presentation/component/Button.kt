package com.hgh.ttoklip_manger.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.ttoklip_manger.presentation.theme.Gray100
import com.hgh.ttoklip_manger.presentation.theme.Gray500
import com.hgh.ttoklip_manger.presentation.theme.MainPurple900
import com.hgh.ttoklip_manger.presentation.theme.Typography

@Composable
fun BasicButton(
    text: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    buttonColor: Color = MainPurple900,
    textColor: Color = Gray100,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(52.dp),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContentColor = Gray500
        ),
        elevation = null,
    ) {
        Text(
            text = text,
            color = if (enabled) textColor else Gray500,
            style = Typography.titleMedium
        )
    }
}

@Preview
@Composable
fun PlanzMainButtonPreview() {
    BasicButton(
        text = "메인 버튼",
        enabled = true,
        modifier = Modifier,
        onClick = {}
    )
}
