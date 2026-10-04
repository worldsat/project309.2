package com.makancompany.project309.ui.customizer

import androidx.lifecycle.ViewModel
import com.makancompany.project309.data.repository.DrinkCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CustomizerViewModel(initialDrinkId: String = "caramel_macchiato") : ViewModel() {

    private val _uiState = MutableStateFlow(
        DrinkCatalog.getDrinkDetail(initialDrinkId).toCustomizerUiState()
    )
    val uiState: StateFlow<CustomizerUiState> = _uiState.asStateFlow()

    fun loadDrink(drinkId: String) {
        val detail = DrinkCatalog.getDrinkDetail(drinkId)
        _uiState.value = detail.toCustomizerUiState()
    }

    fun onAction(action: CustomizerAction) {
        when (action) {
            is CustomizerAction.LoadDrink -> {
                loadDrink(action.drinkId)
            }
            is CustomizerAction.SelectServingStyle -> {
                _uiState.update { it.copy(servingStyle = action.style) }
            }
            is CustomizerAction.SelectCupSize -> {
                _uiState.update { it.copy(cupSize = action.size) }
            }
            is CustomizerAction.AdjustShots -> {
                _uiState.update { current ->
                    val newShots = (current.shots + action.delta).coerceIn(1, 4)
                    current.copy(shots = newShots)
                }
            }
            is CustomizerAction.SelectMilk -> {
                _uiState.update { it.copy(selectedMilk = action.milk) }
            }
            is CustomizerAction.SelectSweetness -> {
                _uiState.update { it.copy(selectedSweetness = action.sweetness) }
            }
            is CustomizerAction.SelectIce -> {
                _uiState.update { it.copy(selectedIce = action.ice) }
            }
            is CustomizerAction.UpdateBaristaNotes -> {
                _uiState.update { it.copy(baristaNotes = action.notes) }
            }
            is CustomizerAction.AdjustQuantity -> {
                _uiState.update { current ->
                    val newQuantity = (current.quantity + action.delta).coerceIn(1, 10)
                    current.copy(quantity = newQuantity)
                }
            }
            is CustomizerAction.AddToCart -> {
                _uiState.update { current ->
                    current.copy(
                        userMessage = "Added ${current.quantity}x ${current.productName} to Cart (${current.formattedTotalPrice})"
                    )
                }
            }
            is CustomizerAction.DismissMessage -> {
                _uiState.update { it.copy(userMessage = null) }
            }
            is CustomizerAction.MoreOptionsClicked -> {
                _uiState.update { it.copy(userMessage = "Product options & nutritional facts") }
            }
            is CustomizerAction.VolumeGuideClicked -> {
                _uiState.update { it.copy(userMessage = "Small: 8oz • Medium: 12oz • Large: 16oz") }
            }
        }
    }
}
