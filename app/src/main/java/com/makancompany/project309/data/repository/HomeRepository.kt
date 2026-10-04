package com.makancompany.project309.data.repository

import com.makancompany.project309.R
import com.makancompany.project309.data.model.BrewCraftClubInfo
import com.makancompany.project309.data.model.Category
import com.makancompany.project309.data.model.DrinkItem
import com.makancompany.project309.data.model.PromoItem

class HomeRepository {

    fun getInitialDrinks(): List<DrinkItem> = listOf(
        DrinkItem(
            id = "caramel_macchiato",
            name = "Caramel Macchiato",
            description = "Layered espresso, steamed milk, vanilla & caramel drizzle.",
            price = 4.85,
            rating = 4.9,
            reviewCount = "1.2k",
            imageRes = R.drawable.caramel_macchiato,
            isFavorite = false
        ),
        DrinkItem(
            id = "iced_spanish_latte",
            name = "Iced Spanish Latte",
            description = "Sweetened condensed milk, double shot espresso, dash of cinnamon.",
            price = 5.20,
            rating = 4.8,
            reviewCount = "850",
            imageRes = R.drawable.iced_spanish_latte,
            isFavorite = false
        ),
        DrinkItem(
            id = "nitro_cold_brew",
            name = "Nitro Cold Brew",
            description = "Velvety cascade infused with nitrogen for smooth texture.",
            price = 4.50,
            rating = 4.9,
            reviewCount = "2.1k",
            imageRes = R.drawable.nitro_cold_brew,
            isFavorite = false
        ),
        DrinkItem(
            id = "oat_milk_flat_white",
            name = "Oat Milk Flat White",
            description = "Ristretto double shot blended with microfoam oat milk.",
            price = 4.95,
            rating = 4.7,
            reviewCount = "630",
            imageRes = R.drawable.oat_milk_flat_white,
            isFavorite = false
        )
    )

    fun getCategories(): List<Category> = Category.values().toList()

    fun getClubInfo(): BrewCraftClubInfo = BrewCraftClubInfo(
        currentBeans = 140,
        maxBeans = 200,
        nextRewardTitle = "Handcrafted Pourover",
        title = "BrewCraft Club"
    )

    fun getPromoItem(): PromoItem = PromoItem()
}
