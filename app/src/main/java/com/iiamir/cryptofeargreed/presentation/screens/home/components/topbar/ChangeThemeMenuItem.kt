package com.iiamir.cryptofeargreed.presentation.screens.home.components.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.theme.TEN
import com.iiamir.cryptofeargreed.presentation.theme.TWENTY
import com.iiamir.cryptofeargreed.presentation.theme.menuItemTextStyle
import com.iiamir.cryptofeargreed.utils.Constants.IS_IN_DARK_MODE
import com.iiamir.cryptofeargreed.utils.Constants.IS_IN_Light_MODE

@Composable
fun ChangeThemeMenuItem(
    isInDarkMode: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.size(TWENTY),
            painter = if (isInDarkMode)
                painterResource(id = R.drawable.round_wb_sunny_24)
            else painterResource(
                id = R.drawable.round_dark_mode_24
            ),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(start = TEN),
            text = if (isInDarkMode) IS_IN_DARK_MODE else IS_IN_Light_MODE,
            style = menuItemTextStyle
        )

    }
}