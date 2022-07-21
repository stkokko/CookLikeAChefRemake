package com.cooking.cooklikeachef.presentation.screens.home_screen.events

import android.content.Context

sealed class HomeUIEvents {
    class ContactUs(val context: Context) : HomeUIEvents()
    object SignOff : HomeUIEvents()
    object DisplayOptionsMenu : HomeUIEvents()
    object DismissOptionsMenu : HomeUIEvents()
    object OpenExitAppDialog: HomeUIEvents()
    object DismissExitAppDialog: HomeUIEvents()
}
