package com.makancompany.project309.data.repository

import androidx.annotation.DrawableRes
import com.makancompany.project309.R
import com.makancompany.project309.ui.customizer.CupSize
import com.makancompany.project309.ui.customizer.CustomizerUiState
import com.makancompany.project309.ui.customizer.IceLevel
import com.makancompany.project309.ui.customizer.MilkOption
import com.makancompany.project309.ui.customizer.ServingStyle
import com.makancompany.project309.ui.customizer.SweetnessLevel
import com.makancompany.project309.ui.home.DrinkItem
import java.util.Locale

data class DrinkDetailData(
    val id: String,
    val name: String,
    val subtitle: String,
    val category: String,
    val basePrice: Double,
    val rating: Double,
    val reviewCount: String,
    val fullReviewCount: String,
    val calories: String,
    val description: String,
    @get:DrawableRes val imageRes: Int,
    val tag1: String = "100% Arabica",
    val tag2: String = "Medium Roast",
    val defaultServingStyle: ServingStyle = ServingStyle.Iced,
    val defaultCupSize: CupSize = CupSize.Medium,
    val defaultShots: Int = 2,
    val defaultMilk: MilkOption = MilkOption.Oat,
    val defaultSweetness: SweetnessLevel = SweetnessLevel.Standard50,
    val defaultIce: IceLevel = IceLevel.LessIce
) {
    fun toHomeDrinkItem(): DrinkItem = DrinkItem(
        id = id,
        name = name,
        subtitle = subtitle,
        category = category,
        price = String.format(Locale.US, "$%.2f", basePrice),
        priceValue = basePrice,
        rating = rating,
        reviewCount = reviewCount,
        imageRes = imageRes
    )

    fun toCustomizerUiState(): CustomizerUiState = CustomizerUiState(
        drinkId = id,
        productName = name,
        basePrice = basePrice,
        rating = rating,
        reviewCount = fullReviewCount,
        calories = calories,
        description = description,
        heroImageRes = imageRes,
        tag1 = tag1,
        tag2 = tag2,
        servingStyle = defaultServingStyle,
        cupSize = defaultCupSize,
        shots = defaultShots,
        selectedMilk = defaultMilk,
        selectedSweetness = defaultSweetness,
        selectedIce = defaultIce
    )
}

object DrinkCatalog {

    val drinks: List<DrinkDetailData> = listOf(
        DrinkDetailData(
            id = "caramel_macchiato",
            name = "Caramel Macchiato",
            subtitle = "Layered espresso, steamed milk, vanilla & caramel drizzle.",
            category = "Hot & Iced",
            basePrice = 4.85,
            rating = 4.9,
            reviewCount = "1.2k",
            fullReviewCount = "(1,240 reviews)",
            calories = "180 kcal",
            description = "Rich espresso poured over vanilla-infused textured milk and finished with our signature handcrafted caramel drizzle.",
            imageRes = R.drawable.caramel_macchiato,
            tag1 = "100% Arabica",
            tag2 = "Medium Roast",
            defaultServingStyle = ServingStyle.Iced,
            defaultCupSize = CupSize.Medium,
            defaultShots = 2,
            defaultMilk = MilkOption.Oat,
            defaultSweetness = SweetnessLevel.Standard50,
            defaultIce = IceLevel.LessIce
        ),
        DrinkDetailData(
            id = "iced_spanish_latte",
            name = "Iced Spanish Latte",
            subtitle = "Sweetened condensed milk, double shot espresso, dash of cinnamon.",
            category = "Sweet & Creamy",
            basePrice = 5.20,
            rating = 4.8,
            reviewCount = "850",
            fullReviewCount = "(850 reviews)",
            calories = "210 kcal",
            description = "Bold espresso blended with sweetened condensed milk and fresh whole milk over ice, dusted with fragrant ground cinnamon.",
            imageRes = R.drawable.iced_spanish_latte,
            tag1 = "Signature Blend",
            tag2 = "Dark Roast",
            defaultServingStyle = ServingStyle.Iced,
            defaultCupSize = CupSize.Medium,
            defaultShots = 2,
            defaultMilk = MilkOption.Whole,
            defaultSweetness = SweetnessLevel.Standard50,
            defaultIce = IceLevel.RegularIce
        ),
        DrinkDetailData(
            id = "nitro_cold_brew",
            name = "Nitro Cold Brew",
            subtitle = "Velvety cascade infused with nitrogen for smooth texture.",
            category = "Reserve Cold",
            basePrice = 4.50,
            rating = 4.9,
            reviewCount = "2.1k",
            fullReviewCount = "(2,100 reviews)",
            calories = "5 kcal",
            description = "Slow-steeped craft cold brew infused with food-grade nitrogen for a velvety cascade, ultra-smooth body, and rich micro-foam crema head.",
            imageRes = R.drawable.nitro_cold_brew,
            tag1 = "Single Origin",
            tag2 = "Nitrogen Infused",
            defaultServingStyle = ServingStyle.Iced,
            defaultCupSize = CupSize.Medium,
            defaultShots = 1,
            defaultMilk = MilkOption.Whole,
            defaultSweetness = SweetnessLevel.NoSugar,
            defaultIce = IceLevel.NoIce
        ),
        DrinkDetailData(
            id = "oat_milk_flat_white",
            name = "Oat Milk Flat White",
            subtitle = "Ristretto double shot blended with microfoam oat milk.",
            category = "Artisanal Hot",
            basePrice = 4.95,
            rating = 4.7,
            reviewCount = "630",
            fullReviewCount = "(630 reviews)",
            calories = "140 kcal",
            description = "Expertly pulled ristretto double shot blended with silky steamed barista-grade oat milk, finished with microfoam latte art.",
            imageRes = R.drawable.oat_milk_flat_white,
            tag1 = "Ristretto Double",
            tag2 = "Blonde Roast",
            defaultServingStyle = ServingStyle.Hot,
            defaultCupSize = CupSize.Medium,
            defaultShots = 2,
            defaultMilk = MilkOption.Oat,
            defaultSweetness = SweetnessLevel.NoSugar,
            defaultIce = IceLevel.NoIce
        ),
        DrinkDetailData(
            id = "autumn_maple_latte",
            name = "Autumn Maple Latte",
            subtitle = "Single origin blend infused with Vermont maple & oat milk.",
            category = "Signature Lattes",
            basePrice = 4.95,
            rating = 4.9,
            reviewCount = "340",
            fullReviewCount = "(340 reviews)",
            calories = "230 kcal",
            description = "Dark-roasted single origin blend infused with pure Vermont maple and velvety steamed oat milk, garnished with spiced nutmeg.",
            imageRes = R.drawable.banner,
            tag1 = "Limited Edition",
            tag2 = "Vermont Maple",
            defaultServingStyle = ServingStyle.Hot,
            defaultCupSize = CupSize.Medium,
            defaultShots = 2,
            defaultMilk = MilkOption.Oat,
            defaultSweetness = SweetnessLevel.Standard50,
            defaultIce = IceLevel.NoIce
        ),
        DrinkDetailData(
            id = "almond_croissant",
            name = "Almond Croissant",
            subtitle = "Warm & flaky pastry filled with rich almond cream.",
            category = "Pastries",
            basePrice = 3.80,
            rating = 4.9,
            reviewCount = "420",
            fullReviewCount = "(420 reviews)",
            calories = "320 kcal",
            description = "Twice-baked buttery Parisian croissant filled with rich frangipane almond cream and topped with toasted sliced almonds.",
            imageRes = R.drawable.almond_croissant,
            tag1 = "Freshly Baked",
            tag2 = "Artisan Bakery",
            defaultServingStyle = ServingStyle.Hot,
            defaultCupSize = CupSize.Small,
            defaultShots = 1,
            defaultMilk = MilkOption.Whole,
            defaultSweetness = SweetnessLevel.Standard50,
            defaultIce = IceLevel.NoIce
        )
    )

    fun getDrinkDetail(id: String): DrinkDetailData {
        return drinks.find { it.id.equals(id, ignoreCase = true) }
            ?: drinks.find { it.name.contains(id, ignoreCase = true) }
            ?: drinks.first()
    }

    fun getHomeDrinkItems(): List<DrinkItem> {
        return drinks.map { it.toHomeDrinkItem() }
    }
}
