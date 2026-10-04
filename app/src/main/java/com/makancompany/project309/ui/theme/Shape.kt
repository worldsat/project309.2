package com.makancompany.project309.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val BrewCraftShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),    // Micro chips, tag indicators
    small = RoundedCornerShape(8.dp),         // Small buttons, stepper icons
    medium = RoundedCornerShape(16.dp),       // Food & drink cards, input boxes
    large = RoundedCornerShape(24.dp),        // Featured promo hero banner, QR card container
    extraLarge = RoundedCornerShape(32.dp)    // Bottom sheets, full rounded pills
)

val PillShape = RoundedCornerShape(9999.dp)   // CTAs, segmented control options, status pills
val TopRoundedSheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
