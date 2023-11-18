package com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.iiamir.cryptofeargreed.presentation.theme.menuItemTextStyle

@Composable
fun PieGraphCategoryMenuItem(
    itemText:String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = itemText,
            style = menuItemTextStyle.copy(textAlign = TextAlign.Center)
        )
    }
}