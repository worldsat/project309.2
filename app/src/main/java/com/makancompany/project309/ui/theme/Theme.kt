package com.makancompany.project309.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val BrewCraftLightColorScheme = lightColorScheme(
    primary = EspressoPrimary,
    onPrimary = EspressoOnPrimary,
    primaryContainer = EspressoPrimaryContainer,
    onPrimaryContainer = EspressoOnPrimaryContainer,
    secondary = CaramelSecondary,
    onSecondary = CaramelOnSecondary,
    secondaryContainer = CaramelSecondaryContainer,
    onSecondaryContainer = CaramelOnSecondaryContainer,
    tertiary = MochaTertiary,
    onTertiary = MochaOnTertiary,
    tertiaryContainer = MochaTertiaryContainer,
    onTertiaryContainer = MochaOnTertiaryContainer,
    background = SandSurface,
    onBackground = SandOnSurface,
    surface = SandSurface,
    onSurface = SandOnSurface,
    surfaceVariant = SandSurfaceVariant,
    onSurfaceVariant = SandOnSurfaceVariant,
    surfaceTint = Color(0xFF785748),
    outline = EspressoOutline,
    outlineVariant = EspressoOutlineVariant,
    error = ErrorColor,
    onError = OnErrorColor,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer
)

@Composable
fun Project309Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = BrewCraftLightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                window.navigationBarColor = colorScheme.surface.toArgb()
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = true
                insetsController.isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BrewCraftTypography,
        shapes = BrewCraftShapes,
        content = content
    )
}