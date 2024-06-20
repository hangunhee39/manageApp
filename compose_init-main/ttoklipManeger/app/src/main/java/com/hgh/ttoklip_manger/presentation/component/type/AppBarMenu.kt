package com.hgh.ttoklip_manger.presentation.component.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgh.ttoklip_manger.R

enum class AppBarMenu(
    val horizontalPadding: Dp,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
) {
    BACK(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_exit_24,
        contentDescription = R.string.back_description,
    ),

    CREATE(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_create,
        contentDescription = R.string.create_description,
    ),
}
