package com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants.KEYBOARD_ARROW_DOWN_ICON

@Composable
fun PieGraphCategory(
    selectedCategoryItem: String,
    pieGraphCategoryItem: List<String>,
    onItemCategoryClicked: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val rotateState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(FIFTY),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Row(
            modifier = Modifier
                .width(ONE_HUNDRED)
                .height(FOURTY)
                .clip(RoundedCornerShape(FIVE))
                .background(MaterialTheme.colors.secondary)
                .clickable {
                    expanded = true
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = selectedCategoryItem,
                style = chartCategoryNameTextStyle
            )
            Box(
                Modifier
                    .fillMaxWidth(0.5f)
                    .rotate(rotateState),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                    contentDescription = KEYBOARD_ARROW_DOWN_ICON,
                    tint = MaterialTheme.colors.onSecondary
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colors.secondary)
            ) {
                pieGraphCategoryItem.forEach { category ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .height(FOURTY),
                        onClick = {
                            expanded = false
                            onItemCategoryClicked(category)
                        }) {
                        PieGraphCategoryMenuItem(itemText = category)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(THIRTY))
    }
}