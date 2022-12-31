package com.example.cryptofeargreed.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptofeargreed.ui.theme.DarkerBlue

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkerBlue),
    ) {
        Text(
            modifier = Modifier
                .weight(0.8f)
                .padding(10.dp),
            text = "Fear & Greed Index",
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = MaterialTheme.typography.h5.fontSize
        )
    }
}