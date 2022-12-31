package com.example.cryptofeargreed.ui.home

sealed class UIEvent {
    class ShowSnackBar(val message: String) : UIEvent()
}
