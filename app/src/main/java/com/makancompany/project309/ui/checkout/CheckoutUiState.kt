package com.makancompany.project309.ui.checkout

import androidx.annotation.DrawableRes
import com.makancompany.project309.R
import java.util.Locale

enum class FulfillmentMode(
    val title: String,
    val timing: String,
    val fee: Double,
    val feeLabel: String
) {
    Pickup(
        title = "Pickup",
        timing = "10-15 mins",
        fee = 0.50,
        feeLabel = "Pickup Packaging & Prep"
    ),
    Delivery(
        title = "Fast Delivery",
        timing = "25-30 mins",
        fee = 2.50,
        feeLabel = "Delivery & Dispatch Fee"
    )
}

data class CartItem(
    val id: String,
    val name: String,
    val description: String,
    val unitPrice: Double,
    val quantity: Int,
    @DrawableRes val imageRes: Int
) {
    val linePrice: Double
        get() = unitPrice * quantity

    val formattedUnitPrice: String
        get() = String.format(Locale.US, "$%.2f", unitPrice)
}

enum class PaymentMethod(
    val title: String,
    val subtitle: String,
    val badge: String? = null
) {
    Balance(
        title = "BrewCraft Balance",
        subtitle = "Available: $24.50",
        badge = "Fastest"
    ),
    DigitalWallet(
        title = "Apple Pay / Google Pay",
        subtitle = "Default device card"
    ),
    Card(
        title = "Mastercard ending in 4242",
        subtitle = "Expires 09/27"
    )
}

data class CheckoutUiState(
    val branchName: String = "Downtown Roastery",
    val fulfillmentMode: FulfillmentMode = FulfillmentMode.Pickup,
    val items: List<CartItem> = listOf(
        CartItem(
            id = "cart-item-1",
            name = "Caramel Macchiato",
            description = "Medium • Oat Milk • 50% Sweetness • Less Ice",
            unitPrice = 5.95,
            quantity = 1,
            imageRes = R.drawable.caramel_macchiato
        ),
        CartItem(
            id = "cart-item-2",
            name = "Almond Croissant",
            description = "Warm & toasted • Butter glaze",
            unitPrice = 3.80,
            quantity = 1,
            imageRes = R.drawable.almond_croissant
        )
    ),
    val voucherCodeInput: String = "BREWFIRST",
    val voucherApplied: Boolean = true,
    val voucherDiscountAmount: Double = 2.00,
    val selectedPayment: PaymentMethod = PaymentMethod.Balance,
    val isSubmittingOrder: Boolean = false,
    val orderSubmitted: Boolean = false,
    val userMessage: String? = null
) {
    val subtotal: Double
        get() = items.sumOf { it.unitPrice * it.quantity }

    val fulfillmentFee: Double
        get() = if (items.isNotEmpty()) fulfillmentMode.fee else 0.0

    val discount: Double
        get() = if (voucherApplied && items.isNotEmpty()) voucherDiscountAmount else 0.0

    // Grounded formula from rules.md (Rule 771) to ensure stable $0.70 tax and $8.95 total
    val taxableAmount: Double
        get() = (subtotal + fulfillmentFee - discount).coerceAtLeast(0.0)

    val estimatedTax: Double
        get() = if (items.isNotEmpty()) (taxableAmount * 0.085) else 0.0

    val totalAmount: Double
        get() = (subtotal + fulfillmentFee - discount + estimatedTax).coerceAtLeast(0.0)

    val formattedSubtotal: String
        get() = String.format(Locale.US, "$%.2f", subtotal)

    val formattedFee: String
        get() = String.format(Locale.US, "$%.2f", fulfillmentFee)

    val formattedDiscount: String
        get() = String.format(Locale.US, "-$%.2f", discount)

    val formattedTax: String
        get() = String.format(Locale.US, "$%.2f", estimatedTax)

    val formattedTotal: String
        get() = String.format(Locale.US, "$%.2f", totalAmount)
}
