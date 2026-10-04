package com.makancompany.project309.ui.checkout

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CheckoutViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    fun onAction(action: CheckoutAction) {
        when (action) {
            is CheckoutAction.SelectFulfillment -> {
                _uiState.update { it.copy(fulfillmentMode = action.mode) }
            }
            is CheckoutAction.UpdateQuantity -> {
                _uiState.update { current ->
                    val updatedItems = current.items.map { item ->
                        if (item.id == action.itemId) {
                            val newQty = (item.quantity + action.delta).coerceIn(1, 10)
                            item.copy(quantity = newQty)
                        } else {
                            item
                        }
                    }
                    current.copy(items = updatedItems)
                }
            }
            is CheckoutAction.RemoveItem -> {
                _uiState.update { current ->
                    val removedItem = current.items.find { it.id == action.itemId }
                    val updatedItems = current.items.filterNot { it.id == action.itemId }
                    current.copy(
                        items = updatedItems,
                        userMessage = removedItem?.let { "Removed ${it.name} from bag" }
                    )
                }
            }
            is CheckoutAction.UpdateVoucherInput -> {
                _uiState.update { it.copy(voucherCodeInput = action.code) }
            }
            is CheckoutAction.ApplyVoucher -> {
                _uiState.update { current ->
                    if (current.voucherCodeInput.isNotBlank()) {
                        current.copy(
                            voucherApplied = true,
                            userMessage = "${current.voucherCodeInput.trim()} coupon applied (-$2.00)"
                        )
                    } else current
                }
            }
            is CheckoutAction.RemoveVoucher -> {
                _uiState.update { current ->
                    current.copy(
                        voucherApplied = false,
                        voucherCodeInput = "",
                        userMessage = "Voucher removed"
                    )
                }
            }
            is CheckoutAction.SelectPayment -> {
                _uiState.update { it.copy(selectedPayment = action.payment) }
            }
            is CheckoutAction.PlaceOrder -> {
                _uiState.update { current ->
                    current.copy(
                        isSubmittingOrder = false,
                        orderSubmitted = true,
                        userMessage = "Order Placed! #BC-8942 • Downtown Roastery"
                    )
                }
            }
            is CheckoutAction.AddMoreItemsClicked -> {
                _uiState.update { it.copy(userMessage = "Browse Bakery & Roastery") }
            }
            is CheckoutAction.AddPaymentMethodClicked -> {
                _uiState.update { it.copy(userMessage = "Add new payment card") }
            }
            is CheckoutAction.DismissMessage -> {
                _uiState.update { it.copy(userMessage = null) }
            }
            is CheckoutAction.MoreOptionsClicked -> {
                _uiState.update { it.copy(userMessage = "Order settings & invoice options") }
            }
            is CheckoutAction.AddCustomizedItem -> {
                _uiState.update { current ->
                    current.copy(
                        items = listOf(action.item) + current.items.filterNot { it.name == action.item.name },
                        userMessage = "Added ${action.item.name} to bag"
                    )
                }
            }
        }
    }
}
