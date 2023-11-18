package com.iiamir.cryptofeargreed.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.iiamir.cryptofeargreed.data.remote.dto.FGDataModel
import com.iiamir.cryptofeargreed.presentation.theme.*

@Composable
fun FearNGreedItem(
    fgDataModel: FGDataModel,
    fgIndexDate: String = "",
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(fearAndGreedItemHeightSize)
            .padding(start = TEN, end = TEN),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.fillMaxHeight(0.4f),
                text = fgIndexDate.ifBlank { fgDataModel.timeStamp },
                style = dateTimeItemsTextStyle,
            )
            Text(
                modifier = Modifier.fillMaxHeight(0.7f),
                text = fgDataModel.valueClassification,
                style = classificationValueItemTextStyle.copy(color = Color(fgDataModel.valueClassificationColor))
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.4f))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f),
                color = MaterialTheme.colors.surface,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.8f)
                .clip(CircleShape)
                .background(Color(fgDataModel.valueClassificationColor)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = fgDataModel.indexValue.toString(),
                style = indexValueItemTextStyle,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}