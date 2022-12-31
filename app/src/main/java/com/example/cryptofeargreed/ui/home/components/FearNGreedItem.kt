package com.example.cryptofeargreed.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptofeargreed.data.remote.dto.FGDataModel
import com.example.cryptofeargreed.data.util.timeStampToYearConverter
import com.example.cryptofeargreed.ui.theme.Gray
import com.example.cryptofeargreed.utils.indexColor

@Composable
fun FearNGreedItem(
    fgDataModel: FGDataModel,
    fgIndexDate: String = "",
) {
    val color = indexColor(fgDataModel.valueClassification)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(0.8f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = fgIndexDate.ifBlank { timeStampToYearConverter(fgDataModel.timeStamp) },
                color = Color.White,
            )
            Text(
                text = fgDataModel.valueClassification,
                color = color
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Gray
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = fgDataModel.indexValue.toString(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}