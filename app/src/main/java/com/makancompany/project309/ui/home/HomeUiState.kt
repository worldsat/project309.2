package com.makancompany.project309.ui.home

import androidx.annotation.DrawableRes
import com.makancompany.project309.R
import com.makancompany.project309.data.repository.DrinkCatalog

enum class BottomNavTab(val title: String) {
    HOME("Home"),
    MENU("Menu"),
    CART("Cart"),
    ACTIVITY("Activity")
}

data class DrinkItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val category: String,
    val price: String,
    val priceValue: Double,
    val rating: Double,
    val reviewCount: String,
    @DrawableRes val imageRes: Int
)

data class PromoBannerItem(
    val badgeTag: String = "Limited Edition",
    val discountTag: String = "20% OFF TODAY",
    val title: String = "Autumn Maple Latte",
    val description: String = "Dark-roasted single origin blend infused with pure Vermont maple and velvety steamed oat milk.",
    val price: String = "$4.95",
    val originalPrice: String = "$6.20",
    @DrawableRes val imageRes: Int = R.drawable.banner
)

data class HomeUiState(
    val greeting: String = "Good morning, Alex ☕",
    val userTier: String = "Tier Gold",
    val branchName: String = "Downtown Roastery, 5th Ave",
    val readyEstimate: String = "Ready in ~10 mins at Downtown Roastery",
    val searchQuery: String = "",
    val loyaltyCurrent: Int = 140,
    val loyaltyMax: Int = 200,
    val loyaltyNextReward: String = "Handcrafted Pourover",
    val categories: List<String> = listOf(
        "All",
        "Espresso",
        "Cold Brew",
        "Pourover",
        "Signature Lattes",
        "Pastries"
    ),
    val selectedCategory: String = "All",
    val promoItem: PromoBannerItem = PromoBannerItem(),
    val popularDrinks: List<DrinkItem> = DrinkCatalog.getHomeDrinkItems(),
    val favorites: Set<String> = emptySet(),
    val cartBadgeCount: Int = 2,
    val selectedTab: BottomNavTab = BottomNavTab.HOME,
    val userMessage: String? = null
)
