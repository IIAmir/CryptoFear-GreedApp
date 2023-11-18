package com.iiamir.cryptofeargreed.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

data class ToggleAndMenuOption(
    val text: String,
    val iconRes: Int? = 0,
    val imageVector: ImageVector = Icons.Default.Menu
)
