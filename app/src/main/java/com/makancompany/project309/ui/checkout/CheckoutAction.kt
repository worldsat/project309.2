package com.makancompany.project309.ui.checkout

sealed interface CheckoutAction {
    data class SelectFulfillment(val mode: FulfillmentMode) : CheckoutAction
    data class UpdateQuantity(val itemId: String, val delta: Int) : CheckoutAction
    data class RemoveItem(val itemId: String) : CheckoutAction
    data class UpdateVoucherInput(val code: String) : CheckoutAction
    data object ApplyVoucher : CheckoutAction
    data object RemoveVoucher : CheckoutAction
    data class SelectPayment(val payment: PaymentMethod) : CheckoutAction
    data object PlaceOrder : CheckoutAction
    data object AddMoreItemsClicked : CheckoutAction
    data object AddPaymentMethodClicked : CheckoutAction
    data object DismissMessage : CheckoutAction
    data object MoreOptionsClicked : CheckoutAction
    data class AddCustomizedItem(val item: CartItem) : CheckoutAction
}
