package com.cooking.cooklikeachef.presentation.screens.main_screen.events

sealed class MainUIEvents {
    object SignOff : MainUIEvents()
    object DisplayOptionsMenu : MainUIEvents()
    object OptionsMenuDismissed : MainUIEvents()
}
