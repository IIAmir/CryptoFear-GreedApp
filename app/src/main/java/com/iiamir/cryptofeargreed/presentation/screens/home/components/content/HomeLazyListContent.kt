package com.iiamir.cryptofeargreed.presentation.screens.home.components.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iiamir.cryptofeargreed.presentation.screens.components.FearNGreedItem
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenClassificationDataState
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenDataState
import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenUIEvent
import com.iiamir.cryptofeargreed.presentation.screens.home.components.animations.TextAlertAnimation
import com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views.NextUpdateFearNGreed
import com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views.PieGraphCategory
import com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs.BarGraph
import com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs.FearNGreedIndexGraph
import com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs.LineGraph
import com.iiamir.cryptofeargreed.presentation.screens.home.components.graphs.PieGraph
import com.iiamir.cryptofeargreed.presentation.screens.home.components.toggle_btn.ToggleButton
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants
import com.iiamir.cryptofeargreed.utils.IndexChart

@Composable
fun HomeLazyListContent(
    homeScreenDataState: HomeScreenDataState,
    homeScreenClassificationDataState: HomeScreenClassificationDataState,
    lastYearIndexIsGone: Boolean,
    isInDarkMode: Boolean,
    onChangeChartAction: (HomeScreenUIEvent) -> Unit,
    onViewAllFGDataClicked: () -> Unit,
    timeUntilUpdateExpiredAction: () -> Unit,
    onItemCategoryClicked: (String) -> Unit,
) {
    if (homeScreenDataState.fgDataModelList.isNotEmpty()) {
        val chartsList =
            homeScreenDataState.fgDataModelList.subList(
                Constants.GET_TODAY_FG_BY_INDEX,
                Constants.GET_LAST_MONTH_FG_BY_INDEX
            ).reversed()

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp * windowSizeScaleNumbers()),
                contentColor = MaterialTheme.colors.secondary,
                backgroundColor = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(bottomStart = TWENTY, bottomEnd = TWENTY),
                elevation = EIGHT
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = TWENTY_FIVE * windowSizeScaleNumbers()),
                        contentAlignment = Alignment.Center
                    ) {
                        FearNGreedIndexGraph(fgDataModel = homeScreenDataState.fgDataModelList[Constants.GET_TODAY_FG_BY_INDEX])
                    }

                    NextUpdateFearNGreed(
                        timeUntilUpdate = homeScreenDataState.timeUntilUpdate,
                        timeUntilUpdateExpiredAction = timeUntilUpdateExpiredAction
                    )
                }
            }

            Spacer(modifier = Modifier.height(FIFTEEN))
            FearNGreedItem(
                fgDataModel = homeScreenDataState.fgDataModelList[Constants.GET_YESTERDAY_FG_BY_INDEX],
                fgIndexDate = Constants.YESTERDAY_TXT
            )
            Spacer(modifier = Modifier.height(FIVE))
            FearNGreedItem(
                fgDataModel = homeScreenDataState.fgDataModelList[Constants.GET_LAST_WEEK_FG_BY_INDEX],
                fgIndexDate = Constants.LAST_WEEK_TXT
            )
            Spacer(modifier = Modifier.height(FIVE))
            FearNGreedItem(
                fgDataModel = homeScreenDataState.fgDataModelList[Constants.GET_LAST_MONTH_FG_BY_INDEX],
                fgIndexDate = Constants.LAST_MONTH_TXT
            )
            Spacer(modifier = Modifier.height(FIVE))

            key(lastYearIndexIsGone) {
                if (!lastYearIndexIsGone)
                    FearNGreedItem(
                        fgDataModel = homeScreenDataState.fgDataModelList[Constants.GET_LAST_YEAR_FG_BY_INDEX],
                        fgIndexDate = Constants.LAST_YEAR_TXT
                    )
            }

            Spacer(modifier = Modifier.height(TEN))

            OutlinedButton(
                onClick = onViewAllFGDataClicked,
                shape = RoundedCornerShape(TEN),
                border = BorderStroke(ONE, MaterialTheme.colors.surface),
                elevation = ButtonDefaults.elevation(ZERO, FIVE),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.secondary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = Constants.VIEW_HISTORICAL_DATA,
                        style = allDataCategoryTextStyle
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = Constants.KEYBOARD_ARROW_RIGHT_ICON,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(TEN))

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.surface
            )

            Spacer(modifier = Modifier.height(TWENTY))

            Text(
                text = Constants.ONE_MONTH_FEAR_N_GREED_INDEX_TXT,
                style = chartCategoryNameTextStyle
            )

            key(isInDarkMode) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = FOURTY * windowSizeScaleNumbers())
                        .height(indexesChartHeightSize)
                ) {
                    when (homeScreenDataState.indexChart) {
                        IndexChart.BAR -> {
                            BarGraph(
                                data = chartsList,
                                modifier = Modifier
                                    .fillMaxSize(),
                                isInDarkMode = isInDarkMode
                            )
                        }

                        IndexChart.LINE -> {
                            LineGraph(
                                data = chartsList,
                                modifier = Modifier
                                    .fillMaxSize(),
                                isInDarkMode = isInDarkMode
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(FIFTY * windowSizeScaleNumbers()))

            ToggleButton(
                modifier = Modifier.padding(end = FIVE),
                selectedItemState = homeScreenDataState.indexChart,
                onClick = { _, selectedItem ->
                    onChangeChartAction(selectedItem)
                }
            )

            Spacer(modifier = Modifier.height(FIFTEEN))

            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.surface
            )

            Spacer(modifier = Modifier.height(TWENTY))

            PieGraphCategory(
                selectedCategoryItem = homeScreenClassificationDataState.selectedClassificationDataCategory,
                pieGraphCategoryItem = homeScreenClassificationDataState.classificationDataCategory,
                onItemCategoryClicked = onItemCategoryClicked
            )

            Spacer(modifier = Modifier.height(TWENTY))

            if (homeScreenClassificationDataState.classificationDataList.isNotEmpty()) {
                PieGraph(
                    modifier = Modifier.fillMaxWidth(),
                    data = homeScreenClassificationDataState.classificationDataList
                )
            } else {
                TextAlertAnimation(textAlert = Constants.SHOW_PIE_CHART_ALERT)
            }

            Spacer(modifier = Modifier.height(FOURTY))
        }
    }
}