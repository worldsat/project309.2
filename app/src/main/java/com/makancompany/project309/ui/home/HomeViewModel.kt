package com.makancompany.project309.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.SelectCategory -> {
                _uiState.update { it.copy(selectedCategory = action.category) }
            }
            is HomeAction.ToggleFavorite -> {
                _uiState.update { current ->
                    val updatedFavorites = if (current.favorites.contains(action.drinkId)) {
                        current.favorites - action.drinkId
                    } else {
                        current.favorites + action.drinkId
                    }
                    current.copy(favorites = updatedFavorites)
                }
            }
            is HomeAction.AddToCart -> {
                _uiState.update { current ->
                    current.copy(
                        cartBadgeCount = current.cartBadgeCount + 1,
                        userMessage = "Added ${action.drink.name} to bag"
                    )
                }
            }
            is HomeAction.TryPromoNow -> {
                _uiState.update { current ->
                    current.copy(
                        cartBadgeCount = current.cartBadgeCount + 1,
                        userMessage = "Added ${current.promoItem.title} to bag"
                    )
                }
            }
            is HomeAction.UpdateSearchQuery -> {
                _uiState.update { it.copy(searchQuery = action.query) }
            }
            is HomeAction.SelectTab -> {
                _uiState.update { it.copy(selectedTab = action.tab) }
            }
            is HomeAction.DismissMessage -> {
                _uiState.update { it.copy(userMessage = null) }
            }
            is HomeAction.NotificationClicked -> {
                _uiState.update { it.copy(userMessage = "2 unread club offers available") }
            }
            is HomeAction.BranchSelectorClicked -> {
                _uiState.update { it.copy(userMessage = "Downtown Roastery (Pick up ready)") }
            }
            is HomeAction.ViewPerksClicked -> {
                _uiState.update { it.copy(userMessage = "Next Tier: Platinum at 400 beans") }
            }
            is HomeAction.VoiceSearchClicked -> {
                _uiState.update { it.copy(userMessage = "Listening for coffee orders...") }
            }
            is HomeAction.FilterSearchClicked -> {
                _uiState.update { it.copy(userMessage = "Filter: All Roasts & Brew Types") }
            }
            is HomeAction.OpenDrinkDetail -> {
                _uiState.update { it.copy(userMessage = "Selected ${action.drinkId}") }
            }
        }
    }
}
