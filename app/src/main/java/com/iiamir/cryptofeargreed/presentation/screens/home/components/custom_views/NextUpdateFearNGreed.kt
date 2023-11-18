package com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.iiamir.cryptofeargreed.data.util.dateStampConverter
import com.iiamir.cryptofeargreed.presentation.screens.home.components.animations.TextAlertAnimation
import com.iiamir.cryptofeargreed.presentation.theme.FIVE
import com.iiamir.cryptofeargreed.presentation.theme.TEN
import com.iiamir.cryptofeargreed.presentation.theme.timeUntilUpdateTextStyle
import com.iiamir.cryptofeargreed.presentation.theme.timeUntilUpdateTimeTextStyle
import com.iiamir.cryptofeargreed.utils.Constants.NEXT_UPDATE_TXT
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_SCREEN_NETWORK_ERROR_MESSAGE
import kotlinx.coroutines.delay

@Composable
fun NextUpdateFearNGreed(
    timeUntilUpdate: Int?,
    timeUntilUpdateExpiredAction: () -> Unit
) {

    Text(
        text = NEXT_UPDATE_TXT,
        style = timeUntilUpdateTextStyle
    )

    Spacer(modifier = Modifier.height(FIVE))

    if (timeUntilUpdate != null) {
        var timeUntilUpdateState by remember(timeUntilUpdate) {
            mutableStateOf(timeUntilUpdate)
        }
        LaunchedEffect(key1 = timeUntilUpdateState) { // Time Count Down
            delay(1000)
            timeUntilUpdateState = (timeUntilUpdateState - 1)
            if (timeUntilUpdateState <= -1) {
                timeUntilUpdateExpiredAction()
            }
        }
        Text(
            text = dateStampConverter(timeUntilUpdateState),
            style = timeUntilUpdateTimeTextStyle
        )
    } else {
        TextAlertAnimation(
            textAlert = SHOW_SCREEN_NETWORK_ERROR_MESSAGE
        )
    }
    Spacer(modifier = Modifier.height(TEN))

}