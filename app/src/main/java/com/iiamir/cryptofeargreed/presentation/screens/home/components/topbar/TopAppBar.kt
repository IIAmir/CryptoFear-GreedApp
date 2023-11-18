package com.iiamir.cryptofeargreed.presentation.screens.home.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iiamir.cryptofeargreed.presentation.theme.FIFTY
import com.iiamir.cryptofeargreed.presentation.theme.TEN
import com.iiamir.cryptofeargreed.presentation.theme.toolbarTextStyle
import com.iiamir.cryptofeargreed.utils.CheckAndroidVersion
import com.iiamir.cryptofeargreed.utils.Constants.ABOUT
import com.iiamir.cryptofeargreed.utils.Constants.APP_NAME
import com.iiamir.cryptofeargreed.utils.Constants.EXIT_APP
import com.iiamir.cryptofeargreed.utils.Constants.RATE_APP
import com.iiamir.cryptofeargreed.utils.Constants.SEND_FEEDBACK
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_LAST_YEAR_INDEX
import com.iiamir.cryptofeargreed.utils.ToggleAndMenuOption

@Composable
fun TopAppBar(
    dropDownMenuItem: List<ToggleAndMenuOption> = listOf(
        ToggleAndMenuOption(text = ABOUT, imageVector = Icons.Default.Info),
        ToggleAndMenuOption(text = SHOW_LAST_YEAR_INDEX, imageVector = Icons.Default.Add),
        ToggleAndMenuOption(text = SEND_FEEDBACK, imageVector = Icons.Default.Send),
        ToggleAndMenuOption(text = RATE_APP, imageVector = Icons.Default.Star),
        ToggleAndMenuOption(text = EXIT_APP, imageVector = Icons.Default.ExitToApp),
    ),
    isInDarkMode: Boolean,
    onChangeThemeClicked: () -> Unit,
    onShowLastYearIndexClicked: () -> Unit,
    onItemClicked: (menuOption: ToggleAndMenuOption) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(FIFTY),
        color = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(TEN),
                text = APP_NAME,
                style = toolbarTextStyle
            )

            if (CheckAndroidVersion) {
                ThemeSwitcher(darkTheme = isInDarkMode, onClick = onChangeThemeClicked)
            }

            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                ) {
                    dropDownMenuItem.forEach { data ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            if (data.text != SHOW_LAST_YEAR_INDEX) onItemClicked(data) else {
                                onShowLastYearIndexClicked()
                            }
                        }) {
                            DropDownMenuItem(itemText = data.text, itemIcon = data.imageVector)
                        }
                    }
                }
            }
        }
    }
}