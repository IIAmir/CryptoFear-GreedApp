package com.iiamir.cryptofeargreed.presentation.screens.home.components.toggle_btn

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants.TOGGLE_BUTTON
import com.iiamir.cryptofeargreed.utils.ToggleAndMenuOption

@Composable
fun SelectionItem(
    option: ToggleAndMenuOption,
    selected: Boolean,
    onClick: (option: ToggleAndMenuOption) -> Unit
) {
    Button(
        onClick = { onClick(option) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.secondary
        ),
        shape = RoundedCornerShape(15),
        elevation = ButtonDefaults.elevation(ZERO, ZERO),
        contentPadding = PaddingValues(ZERO),
        modifier = Modifier.padding(FIVE, ZERO)
    ) {
        Row(
            modifier = Modifier.padding(ZERO),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = option.text,
                style = chartCategoryNameTextStyle.copy(color = if (selected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.primary),
                modifier = Modifier.padding(ZERO)
            )

            if (option.iconRes != null) {
                Icon(
                    painter = painterResource(id = option.iconRes),
                    contentDescription = TOGGLE_BUTTON,
                    tint = if (selected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(FIVE, TWO, TWO, TWO)
                )
            }
        }
    }
}