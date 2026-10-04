package com.makancompany.project309.ui.customizer

import androidx.annotation.DrawableRes
import com.makancompany.project309.R

enum class ServingStyle(val title: String) {
    Iced("Iced"),
    Hot("Hot")
}

enum class CupSize(
    val title: String,
    val volume: String,
    val surcharge: Double,
    val surchargeLabel: String
) {
    Small("Small", "8 oz", 0.00, "Standard"),
    Medium("Medium", "12 oz", 0.60, "+$0.60"),
    Large("Large", "16 oz", 1.20, "+$1.20")
}

enum class MilkOption(
    val title: String,
    val surcharge: Double,
    val displayLabel: String
) {
    Oat("Oat Milk", 0.50, "Oat Milk (+$0.50)"),
    Almond("Almond Milk", 0.50, "Almond Milk"),
    Whole("Whole Milk", 0.00, "Whole Milk"),
    Skim("Skim Milk", 0.00, "Skim Milk"),
    Coconut("Coconut Milk", 0.50, "Coconut Milk")
}

enum class SweetnessLevel(val title: String) {
    NoSugar("No Sugar"),
    Mild25("25% Mild"),
    Standard50("50% Standard"),
    Sweet100("100% Sweet")
}

enum class IceLevel(val title: String) {
    NoIce("No Ice"),
    LessIce("Less Ice"),
    RegularIce("Regular Ice")
}

data class CustomizerUiState(
    val drinkId: String = "caramel_macchiato",
    val productName: String = "Caramel Macchiato",
    val basePrice: Double = 4.85,
    val rating: Double = 4.9,
    val reviewCount: String = "(1,240 reviews)",
    val calories: String = "180 kcal",
    val description: String = "Rich espresso poured over vanilla-infused textured milk and finished with our signature handcrafted caramel drizzle.",
    @get:DrawableRes val heroImageRes: Int = R.drawable.caramel_macchiato,
    val tag1: String = "100% Arabica",
    val tag2: String = "Medium Roast",
    val servingStyle: ServingStyle = ServingStyle.Iced,
    val cupSize: CupSize = CupSize.Medium,
    val shots: Int = 2,
    val selectedMilk: MilkOption = MilkOption.Oat,
    val selectedSweetness: SweetnessLevel = SweetnessLevel.Standard50,
    val selectedIce: IceLevel = IceLevel.LessIce,
    val baristaNotes: String = "",
    val quantity: Int = 1,
    val userMessage: String? = null
) {
    val shotDescription: String
        get() = when (shots) {
            1 -> "Single Espresso (Mild)"
            2 -> "Double Ristretto (Balanced)"
            3 -> "Triple Bold (Robust)"
            else -> "Quad Turbo (Extra Strong)"
        }

    val unitPrice: Double
        get() {
            val sizePrice = cupSize.surcharge
            val milkPrice = selectedMilk.surcharge
            val shotPrice = (shots - 2) * 0.80
            return (basePrice + sizePrice + milkPrice + shotPrice).coerceAtLeast(0.0)
        }

    val totalPrice: Double
        get() = (unitPrice * quantity).coerceAtLeast(0.0)

    val formattedTotalPrice: String
        get() = String.format(java.util.Locale.US, "$%.2f", totalPrice)

    val formattedBasePrice: String
        get() = String.format(java.util.Locale.US, "$%.2f", basePrice)

    fun toCartItem(): com.makancompany.project309.ui.checkout.CartItem {
        val options = mutableListOf<String>()
        options.add(cupSize.title)
        if (servingStyle == ServingStyle.Iced) {
            options.add(selectedIce.title)
        } else {
            options.add("Hot")
        }
        options.add(selectedMilk.title)
        if (selectedSweetness != SweetnessLevel.NoSugar) {
            options.add(selectedSweetness.title)
        }
        return com.makancompany.project309.ui.checkout.CartItem(
            id = "cart-custom-${drinkId}-${System.currentTimeMillis()}",
            name = productName,
            description = options.joinToString(" • "),
            unitPrice = unitPrice,
            quantity = quantity,
            imageRes = heroImageRes
        )
    }
}
