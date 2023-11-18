package com.iiamir.cryptofeargreed.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.iiamir.cryptofeargreed.presentation.theme.FIFTY
import com.iiamir.cryptofeargreed.presentation.theme.TWENTY_FIVE
import com.iiamir.cryptofeargreed.utils.Constants.CLOSE_ICON
import com.iiamir.cryptofeargreed.utils.Constants.SEARCH_HERE_TXT
import com.iiamir.cryptofeargreed.utils.Constants.SEARCH_ICON

@Composable
fun SearchWidget(
    textValue: String,
    onValueChange: (String) -> Unit,
    onBackIconClicked: () -> Unit,
    onCloseIconClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(FIFTY)
            .background(MaterialTheme.colors.secondary)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textValue,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = SEARCH_HERE_TXT,
                    color = MaterialTheme.colors.onSecondary
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSecondary
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    imageVector = Icons.Default.Search,
                    contentDescription = SEARCH_ICON,
                    tint = MaterialTheme.colors.onSecondary
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (textValue.isNotBlank()) onCloseIconClicked() else onBackIconClicked()
                    }
                ) {
                    if (textValue.isNotBlank()) {
                        Icon(
                            painter = painterResource(id = com.iiamir.cryptofeargreed.R.drawable.backspace),
                            contentDescription = CLOSE_ICON,
                            tint = MaterialTheme.colors.onSecondary,
                            modifier = Modifier.size(TWENTY_FIVE)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = CLOSE_ICON,
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colors.secondary,
                unfocusedIndicatorColor = MaterialTheme.colors.secondary,
                backgroundColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onSecondary
            )
        )
    }
}