package com.iiamir.cryptofeargreed.presentation.screens.home.components.toggle_btn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.iiamir.cryptofeargreed.R
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenUIEvent
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants
import com.iiamir.cryptofeargreed.utils.IndexChart
import com.iiamir.cryptofeargreed.utils.ToggleAndMenuOption

@Composable
fun ToggleButton(
    options: Array<ToggleAndMenuOption> = arrayOf(
        ToggleAndMenuOption(
            text = Constants.BAR_CHART,
            iconRes = R.drawable.round_bar_chart_24
        ),
        ToggleAndMenuOption(
            text = Constants.LINE_CHART,
            iconRes = R.drawable.round_timeline_24
        )
    ),
    modifier: Modifier,
    selectedItemState: IndexChart,
    onClick: (selectedOptions: Array<ToggleAndMenuOption>, selectedItem: HomeScreenUIEvent) -> Unit,
) {

    val state = remember {
        mutableStateMapOf<String, ToggleAndMenuOption>()
    }

    OutlinedButton(
        onClick = {},
        border = BorderStroke(ONE, MaterialTheme.colors.surface),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colors.secondary,
            backgroundColor = MaterialTheme.colors.secondary
        ),
        contentPadding = PaddingValues(ZERO, ZERO),
        modifier = modifier
            .padding(ZERO)
            .height(FOURTY)
    ) {
        if (options.isEmpty()) {
            return@OutlinedButton
        }

        val onItemClick: (option: ToggleAndMenuOption) -> Unit = { option ->
            if (selectedItemState == IndexChart.BAR) {
                options.forEach {
                    val key = it.text
                    if (key == option.text) {
                        state[key] = option
                    } else {
                        state.remove(key)
                    }
                }
            } else {
                val key = option.text
                if (!state.contains(key)) {
                    state[key] = option
                } else {
                    state.remove(key)
                }
            }
            onClick(
                state.values.toTypedArray(),
                if (selectedItemState == IndexChart.BAR) HomeScreenUIEvent.ShowLineGraph else HomeScreenUIEvent.ShowBarGraph
            )
        }

        val first = options.first()
        val last = options.last()

        SelectionItem(
            option = first,
            selected = selectedItemState == IndexChart.BAR,
            onClick = onItemClick
        )

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(ONE),
            color = MaterialTheme.colors.surface
        )

        SelectionItem(
            option = last,
            selected = selectedItemState == IndexChart.LINE,
            onClick = onItemClick
        )
    }

}