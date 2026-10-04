package com.makancompany.project309.ui.tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrackerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TrackerUiState())
    val uiState: StateFlow<TrackerUiState> = _uiState.asStateFlow()

    fun onAction(action: TrackerAction) {
        when (action) {
            is TrackerAction.GetDirectionsClicked -> {
                _uiState.update {
                    it.copy(userMessage = "Opening directions to ${it.storeAddress} in Maps...")
                }
            }
            is TrackerAction.CallStoreClicked -> {
                _uiState.update {
                    it.copy(userMessage = "Calling ${it.storeName} at ${it.storePhone}...")
                }
            }
            is TrackerAction.RedeemRewardClicked -> {
                _uiState.update { current ->
                    val reward = current.rewards.find { it.id == action.rewardId }
                    if (reward != null && current.memberBeans >= reward.costBeans) {
                        current.copy(
                            memberBeans = current.memberBeans - reward.costBeans,
                            userMessage = "Redeemed ${reward.title} for ${reward.costBeans} beans!"
                        )
                    } else {
                        current.copy(userMessage = "Not enough beans to redeem this reward yet.")
                    }
                }
            }
            is TrackerAction.DownloadReceiptClicked -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(isGeneratingReceipt = true, userMessage = "Generating PDF Receipt...") }
                    delay(1200)
                    _uiState.update {
                        it.copy(
                            isGeneratingReceipt = false,
                            receiptSaved = true,
                            userMessage = "Order Receipt Saved! (BC-8924.pdf)"
                        )
                    }
                }
            }
            is TrackerAction.NeedHelpClicked -> {
                _uiState.update {
                    it.copy(userMessage = "BrewCraft Concierge: Contacting barista team for ${it.orderNumber}...")
                }
            }
            is TrackerAction.StepChanged -> {
                _uiState.update { it.copy(currentStep = action.step) }
            }
            is TrackerAction.DismissMessage -> {
                _uiState.update { it.copy(userMessage = null) }
            }
            is TrackerAction.MoreOptionsClicked -> {
                _uiState.update { it.copy(userMessage = "Tracker settings & share order link") }
            }
        }
    }
}
