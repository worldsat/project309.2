package com.makancompany.project309.ui.home

sealed interface HomeAction {
    data class SelectCategory(val category: String) : HomeAction
    data class ToggleFavorite(val drinkId: String) : HomeAction
    data class AddToCart(val drink: DrinkItem) : HomeAction
    data object TryPromoNow : HomeAction
    data class UpdateSearchQuery(val query: String) : HomeAction
    data class SelectTab(val tab: BottomNavTab) : HomeAction
    data class OpenDrinkDetail(val drinkId: String) : HomeAction
    data object DismissMessage : HomeAction
    data object NotificationClicked : HomeAction
    data object BranchSelectorClicked : HomeAction
    data object ViewPerksClicked : HomeAction
    data object VoiceSearchClicked : HomeAction
    data object FilterSearchClicked : HomeAction
}
