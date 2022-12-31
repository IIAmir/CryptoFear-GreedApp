package com.example.cryptofeargreed.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptofeargreed.data.remote.dto.FGDataModel
import com.example.cryptofeargreed.data.util.dateStampConverter
import com.example.cryptofeargreed.ui.home.components.CustomComponent
import com.example.cryptofeargreed.ui.home.components.FearNGreedItem
import com.example.cryptofeargreed.ui.home.components.LineChart
import com.example.cryptofeargreed.ui.theme.ContentBlue
import com.example.cryptofeargreed.ui.theme.DarkerBlue
import com.example.cryptofeargreed.ui.theme.Gray
import com.example.cryptofeargreed.utils.Constants.GET_LAST_MONTH_FG_BY_INDEX
import com.example.cryptofeargreed.utils.Constants.GET_LAST_WEEK_FG_BY_INDEX
import com.example.cryptofeargreed.utils.Constants.GET_TODAY_FG_BY_INDEX
import com.example.cryptofeargreed.utils.Constants.GET_YESTERDAY_FG_BY_INDEX
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    getAllFGDataList: List<FGDataModel>,
    getAMonthFGDataList: List<FGDataModel>,
) {

    var timeUntilUpdate by remember {
        mutableStateOf(getAMonthFGDataList[GET_TODAY_FG_BY_INDEX].timeUntilUpdate)
    }

    val time by remember(timeUntilUpdate) {
        mutableStateOf(dateStampConverter(timeUntilUpdate!!))
    }

    LaunchedEffect(key1 = timeUntilUpdate) {
        delay(1000)
        timeUntilUpdate = (timeUntilUpdate!! - 1)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ContentBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentColor = DarkerBlue,
                backgroundColor = DarkerBlue,
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(25.dp))

                    CustomComponent(fgDataModel = getAMonthFGDataList[GET_TODAY_FG_BY_INDEX])

                    Text(
                        text = "The next update will happen in:",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = time,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            FearNGreedItem(fgDataModel = getAMonthFGDataList[GET_YESTERDAY_FG_BY_INDEX],
                fgIndexDate = "Yesterday")
            Spacer(modifier = Modifier.height(5.dp))
            FearNGreedItem(fgDataModel = getAMonthFGDataList[GET_LAST_WEEK_FG_BY_INDEX],
                fgIndexDate = "Last Week")
            Spacer(modifier = Modifier.height(5.dp))
            FearNGreedItem(fgDataModel = getAMonthFGDataList[GET_LAST_MONTH_FG_BY_INDEX],
                fgIndexDate = "Last Month")

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "One Month Fear & Greed Index.", color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))

            LineChart(
                data = getAMonthFGDataList.subList(GET_TODAY_FG_BY_INDEX,
                    GET_LAST_MONTH_FG_BY_INDEX).reversed(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Historical Fear & Greed Values.", color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(getAllFGDataList) { item ->
            FearNGreedItem(item)
        }
    }
}