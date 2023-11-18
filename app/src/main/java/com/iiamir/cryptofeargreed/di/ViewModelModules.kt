package com.iiamir.cryptofeargreed.di

import com.iiamir.cryptofeargreed.presentation.screens.home.HomeScreenViewModel
import com.iiamir.cryptofeargreed.presentation.screens.search.SearchScreenViewModel
import com.iiamir.cryptofeargreed.presentation.screens.splash.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel<SplashScreenViewModel> {
        SplashScreenViewModel(
            dataStoreOperations = get()
        )
    }
    viewModel<HomeScreenViewModel> {
        HomeScreenViewModel(
            dataStoreOperations = get(),
            fgDataRepository = get()
        )
    }
    viewModel<SearchScreenViewModel> {
        SearchScreenViewModel(
            fgDataRepository = get()
        )
    }
}