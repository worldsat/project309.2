package com.makancompany.project309.ui.customizer

sealed interface CustomizerAction {
    data class SelectServingStyle(val style: ServingStyle) : CustomizerAction
    data class SelectCupSize(val size: CupSize) : CustomizerAction
    data class AdjustShots(val delta: Int) : CustomizerAction
    data class SelectMilk(val milk: MilkOption) : CustomizerAction
    data class SelectSweetness(val sweetness: SweetnessLevel) : CustomizerAction
    data class SelectIce(val ice: IceLevel) : CustomizerAction
    data class UpdateBaristaNotes(val notes: String) : CustomizerAction
    data class AdjustQuantity(val delta: Int) : CustomizerAction
    data object AddToCart : CustomizerAction
    data object DismissMessage : CustomizerAction
    data object MoreOptionsClicked : CustomizerAction
    data object VolumeGuideClicked : CustomizerAction
    data class LoadDrink(val drinkId: String) : CustomizerAction
}
