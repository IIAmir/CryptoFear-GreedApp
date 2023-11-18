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
import androidx.compose.ui.graphics.vector.ImageVector
import com.iiamir.cryptofeargreed.presentation.theme.TEN
import com.iiamir.cryptofeargreed.presentation.theme.TWENTY
import com.iiamir.cryptofeargreed.presentation.theme.menuItemTextStyle

@Composable
fun DropDownMenuItem(
    itemText: String,
    itemIcon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.size(TWENTY),
            imageVector = itemIcon,
            contentDescription = itemText
        )
        Text(
            modifier = Modifier
                .padding(start = TEN),
            text = itemText,
            style = menuItemTextStyle
        )
    }
}