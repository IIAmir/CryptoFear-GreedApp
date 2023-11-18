package com.iiamir.cryptofeargreed.presentation.screens.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.theme.FIVE
import com.iiamir.cryptofeargreed.presentation.theme.ONE_HUNDRED
import com.iiamir.cryptofeargreed.utils.Constants.NOT_FOUND_TXT
import com.iiamir.cryptofeargreed.utils.Constants.SEARCH_ICON

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(ONE_HUNDRED),
            painter = painterResource(id = R.drawable.magnifying_glass),
            contentDescription = SEARCH_ICON,
            tint = MaterialTheme.colors.onSecondary
        )
        Text(
            modifier = Modifier
                .padding(top = FIVE),
            text = NOT_FOUND_TXT,
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}