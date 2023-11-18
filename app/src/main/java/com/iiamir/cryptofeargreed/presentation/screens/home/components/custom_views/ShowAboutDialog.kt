package com.iiamir.cryptofeargreed.presentation.screens.home.components.custom_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import com.iiamir.cryptofeargreed.presentation.theme.*
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT_CONTENT_1
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT_CONTENT_2
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT_CONTENT_3
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT_CONTENT_4
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT_TITLE
import com.iiamir.cryptofeargreed.utils.Constants.DISMISS_DIALOG
import com.iiamir.cryptofeargreed.utils.Constants.MORE_INFORMATION

@Composable
fun ShowAboutDialog(
    onDismiss: (Boolean, Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss(false, false) }) {
        Column(
            modifier = Modifier
                .width(aboutDialogWidthSize)
                .height(aboutDialogHeightSize)
                .clip(RoundedCornerShape(TWENTY))
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.primary)
                .padding(start = TEN, end = TEN, bottom = TEN, top = FIVE),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = ABOUT,
                    style = AboutDialogTextStyle
                )

                IconButton(onClick = { onDismiss(false, false) }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = DISMISS_DIALOG,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
            Text(
                modifier = Modifier.fillMaxHeight(0.2f),
                text = ABOUT_TITLE,
                style = AboutDialogTitleTextStyle
            )
            Spacer(modifier = Modifier.height(FIFTEEN))
            Text(
                modifier = Modifier.fillMaxHeight(0.2f),
                text = ABOUT_CONTENT_1,
                style = AboutDialogContentTextStyle
            )
            Spacer(modifier = Modifier.height(EIGHT))
            Text(
                modifier = Modifier.fillMaxHeight(0.2f),
                text = ABOUT_CONTENT_2,
                style = AboutDialogContentTextStyle
            )
            Spacer(modifier = Modifier.height(EIGHT))
            Text(
                modifier = Modifier.fillMaxHeight(0.2f),
                text = ABOUT_CONTENT_3,
                style = AboutDialogContentTextStyle
            )
            Spacer(modifier = Modifier.height(EIGHT))
            Text(
                modifier = Modifier.fillMaxHeight(0.2f),
                text = ABOUT_CONTENT_4,
                style = AboutDialogContentTextStyle
            )
            Spacer(modifier = Modifier.height(FIFTEEN))
            Text(
                modifier = Modifier.clickable { onDismiss(true, true) },
                text = MORE_INFORMATION,
                style = AboutDialogForMoreInformationTextStyle
            )
        }
    }
}