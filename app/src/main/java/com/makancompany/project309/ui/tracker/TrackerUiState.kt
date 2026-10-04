package com.makancompany.project309.ui.tracker

import androidx.annotation.DrawableRes
import com.makancompany.project309.R

enum class TrackerStep(val title: String) {
    Received("Order\nReceived"),
    Brewing("Brewing &\nCrafting"),
    Ready("Ready for\nPickup"),
    Enjoy("Enjoy!")
}

data class RedeemableReward(
    val id: String,
    val title: String,
    val description: String,
    val costBeans: Int,
    @DrawableRes val iconRes: Int
)

data class TrackerUiState(
    val orderNumber: String = "#BC-8924",
    val pickupBar: String = "Pickup Bar 2",
    val estimatedMinutesRemaining: Int = 6,
    val currentStep: TrackerStep = TrackerStep.Brewing,
    val baristaName: String = "Barista Liam",
    val baristaNote: String = "Foaming your velvety oat milk & pulling fresh double origin shots.",
    val productName: String = "1x Iced Oat Honey Latte",
    val productDetails: String = "Large (20oz) • Extra espresso shot • Light ice",
    val storeName: String = "Downtown Roastery",
    val storeAddress: String = "412 5th Ave, New York • 0.3 mi away",
    val storePhone: String = "(212) 555-0194",
    val memberName: String = "Alex",
    val memberBeans: Int = 148,
    val beansBrewing: Int = 12,
    val beansToNextTier: Int = 52,
    val goldTierTarget: Int = 200,
    val progressPercent: Float = 0.74f,
    val rewards: List<RedeemableReward> = listOf(
        RedeemableReward(
            id = "flavor",
            title = "Free Flavor Shot",
            description = "Vanilla, Caramel or Hazelnut",
            costBeans = 30,
            iconRes = R.drawable.ic_liquor
        ),
        RedeemableReward(
            id = "pastry",
            title = "Artisan Pastry",
            description = "Butter Croissant or Almond Danish",
            costBeans = 80,
            iconRes = R.drawable.ic_bakery_dining
        )
    ),
    val isLive: Boolean = true,
    val isGeneratingReceipt: Boolean = false,
    val receiptSaved: Boolean = false,
    val userMessage: String? = null
)
