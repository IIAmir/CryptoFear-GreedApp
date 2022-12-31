package com.example.cryptofeargreed.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptofeargreed.ui.home.FGDataViewModel
import com.example.cryptofeargreed.ui.home.HomeScreen
import com.example.cryptofeargreed.ui.home.UIEvent
import com.example.cryptofeargreed.ui.home.components.TopAppBar
import com.example.cryptofeargreed.ui.theme.ContentBlue
import com.example.cryptofeargreed.ui.theme.CryptoFearGreedTheme
import com.example.cryptofeargreed.ui.theme.DarkerBlue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoFearGreedTheme {

                val fgDataViewModel: FGDataViewModel = hiltViewModel()
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true) {
                    fgDataViewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is UIEvent.ShowSnackBar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    backgroundColor = ContentBlue,
                    scaffoldState = scaffoldState,
                    topBar = { TopAppBar() }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (fgDataViewModel.getAllFGDataState.value.isLoading
                            && fgDataViewModel.getAMonthFGDataState.value.isLoading
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                color = Color.White
                            )
                        }

                        if ((fgDataViewModel.getAllFGDataState.value.error.isNullOrBlank()
                                    && fgDataViewModel.getAMonthFGDataState.value.error.isNullOrBlank())
                            && !fgDataViewModel.getAllFGDataState.value.isLoading
                            && !fgDataViewModel.getAMonthFGDataState.value.isLoading
                        ) {
                            Button(onClick = {
                                fgDataViewModel.getAllFGDataViewModel()
                                fgDataViewModel.getAMonthFGDataViewModel()
                            },
                                colors = ButtonDefaults.buttonColors(backgroundColor = DarkerBlue),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(20.dp)
                            ) {
                                Text(text = "Retry", color = Color.White)
                            }
                        }
                    }

                    if (fgDataViewModel.getAllFGDataState.value.fgDataState.isNotEmpty()
                        && fgDataViewModel.getAMonthFGDataState.value.fgDataState.isNotEmpty()
                    ) {
                        HomeScreen(
                            getAllFGDataList = fgDataViewModel.getAllFGDataState.value.fgDataState,
                            getAMonthFGDataList = fgDataViewModel.getAMonthFGDataState.value.fgDataState
                        )
                    }
                }
            }
        }
    }
}