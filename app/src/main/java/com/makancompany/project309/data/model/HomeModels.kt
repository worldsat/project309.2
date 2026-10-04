package com.makancompany.project309.data.model

import androidx.annotation.DrawableRes
import com.makancompany.project309.R

enum class Category(
    val id: String,
    val title: String,
    @DrawableRes val iconRes: Int
) {
    ALL("all", "All", R.drawable.ic_check),
    ESPRESSO("espresso", "Espresso", R.drawable.ic_cat_espresso),
    COLD_BREW("coldbrew", "Cold Brew", R.drawable.ic_cat_coldbrew),
    POUROVER("pourover", "Pourover", R.drawable.ic_cat_pourover),
    SIGNATURE_LATTES("signature", "Signature Lattes", R.drawable.ic_cat_signature),
    PASTRIES("pastries", "Pastries", R.drawable.ic_cat_pastries)
}

data class DrinkItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val reviewCount: String,
    @DrawableRes val imageRes: Int,
    val isFavorite: Boolean = false
)

data class BrewCraftClubInfo(
    val currentBeans: Int = 140,
    val maxBeans: Int = 200,
    val nextRewardTitle: String = "Handcrafted Pourover",
    val title: String = "BrewCraft Club"
) {
    val progress: Float get() = (currentBeans.toFloat() / maxBeans.toFloat()).coerceIn(0f, 1f)
    val remainingBeans: Int get() = (maxBeans - currentBeans).coerceAtLeast(0)
}

data class PromoItem(
    val tag: String = "Limited Edition",
    val discountBadge: String = "20% OFF TODAY",
    val title: String = "Autumn Maple Latte",
    val description: String = "Dark-roasted single origin blend infused with pure Vermont maple and velvety steamed oat milk.",
    val price: Double = 4.95,
    val originalPrice: Double = 6.20,
    @DrawableRes val imageRes: Int = R.drawable.banner
)

enum class HomeNavTab(
    val id: String,
    val label: String,
    @DrawableRes val iconRes: Int
) {
    HOME("explore", "Home", R.drawable.ic_nav_home),
    MENU("menu", "Menu", R.drawable.ic_nav_menu),
    CART("cart", "Cart", R.drawable.ic_nav_cart),
    ACTIVITY("activity", "Activity", R.drawable.ic_nav_activity)
}
