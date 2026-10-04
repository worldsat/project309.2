package com.makancompany.project309.ui.customizer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.customizer.components.BaristaNotesSection
import com.makancompany.project309.ui.customizer.components.CupSizeSelector
import com.makancompany.project309.ui.customizer.components.CustomizerStickyBottomBar
import com.makancompany.project309.ui.customizer.components.CustomizerTopAppBar
import com.makancompany.project309.ui.customizer.components.EspressoShotsCard
import com.makancompany.project309.ui.customizer.components.IceLevelSection
import com.makancompany.project309.ui.customizer.components.MilkChoiceSection
import com.makancompany.project309.ui.customizer.components.ProductHeroSection
import com.makancompany.project309.ui.customizer.components.ServingStyleSelector
import com.makancompany.project309.ui.customizer.components.SweetnessLevelSection
import com.makancompany.project309.ui.customizer.components.TitleAndRatingSection
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.Project309Theme
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun CustomizerRoute(
    drinkId: String = "caramel_macchiato",
    viewModel: CustomizerViewModel = remember(drinkId) { CustomizerViewModel(drinkId) },
    onNavigateBack: () -> Unit,
    onNavigateToCheckout: (com.makancompany.project309.ui.checkout.CartItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(drinkId) {
        viewModel.onAction(CustomizerAction.LoadDrink(drinkId))
    }

    LaunchedEffect(state.userMessage) {
        state.userMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            viewModel.onAction(CustomizerAction.DismissMessage)
        }
    }

    CustomizerScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateBack = onNavigateBack,
        onNavigateToCheckout = { onNavigateToCheckout(state.toCartItem()) },
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun CustomizerScreen(
    state: CustomizerUiState,
    onAction: (CustomizerAction) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToCheckout: () -> Unit = {},
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CustomizerTopAppBar(
                title = state.productName,
                onBackClick = onNavigateBack,
                onMoreClick = { onAction(CustomizerAction.MoreOptionsClicked) },
                onProfileClick = {}
            )
        },
        bottomBar = {
            CustomizerStickyBottomBar(
                quantity = state.quantity,
                formattedTotalPrice = state.formattedTotalPrice,
                onAdjustQuantity = { onAction(CustomizerAction.AdjustQuantity(it)) },
                onAddToCart = {
                    onAction(CustomizerAction.AddToCart)
                    onNavigateToCheckout()
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .shadow(6.dp, RoundedCornerShape(9999.dp))
                            .clip(RoundedCornerShape(9999.dp))
                            .background(SandOnSurface)
                            .padding(horizontal = 18.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = CaramelSecondaryFixed,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = data.visuals.message,
                            style = MaterialTheme.typography.labelMedium,
                            color = SandSurfaceContainerLowest
                        )
                    }
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 1. Product Showcase Hero
            item(key = "hero_section") {
                ProductHeroSection(
                    imageRes = state.heroImageRes,
                    calories = state.calories,
                    productName = state.productName,
                    tag1 = state.tag1,
                    tag2 = state.tag2
                )
            }

            // 2. Title & Rating Header
            item(key = "title_rating_section") {
                TitleAndRatingSection(
                    productName = state.productName,
                    rating = state.rating,
                    reviewCount = state.reviewCount,
                    formattedBasePrice = state.formattedBasePrice,
                    description = state.description
                )
            }

            // 3. Temperature Toggle (Serving Style)
            item(key = "serving_style_section") {
                ServingStyleSelector(
                    selectedStyle = state.servingStyle,
                    onStyleSelected = { onAction(CustomizerAction.SelectServingStyle(it)) }
                )
            }

            // 4. Cup Size Selector
            item(key = "cup_size_section") {
                CupSizeSelector(
                    selectedSize = state.cupSize,
                    onSizeSelected = { onAction(CustomizerAction.SelectCupSize(it)) },
                    onVolumeGuideClick = { onAction(CustomizerAction.VolumeGuideClicked) }
                )
            }

            // 5. Espresso Shots Counter Card
            item(key = "shots_section") {
                EspressoShotsCard(
                    shots = state.shots,
                    shotDescription = state.shotDescription,
                    onAdjustShots = { onAction(CustomizerAction.AdjustShots(it)) }
                )
            }

            // 6. Choice of Milk
            item(key = "milk_section") {
                MilkChoiceSection(
                    selectedMilk = state.selectedMilk,
                    onMilkSelected = { onAction(CustomizerAction.SelectMilk(it)) }
                )
            }

            // 7. Sweetness Level
            item(key = "sweetness_section") {
                SweetnessLevelSection(
                    selectedSweetness = state.selectedSweetness,
                    onSweetnessSelected = { onAction(CustomizerAction.SelectSweetness(it)) }
                )
            }

            // 8. Ice Level (Only visible when Serving Style == Iced)
            if (state.servingStyle == ServingStyle.Iced) {
                item(key = "ice_level_section") {
                    IceLevelSection(
                        selectedIce = state.selectedIce,
                        onIceSelected = { onAction(CustomizerAction.SelectIce(it)) }
                    )
                }
            }

            // 9. Barista Notes
            item(key = "barista_notes_section") {
                BaristaNotesSection(
                    notes = state.baristaNotes,
                    onNotesChange = { onAction(CustomizerAction.UpdateBaristaNotes(it)) }
                )
            }

            // Bottom Spacer
            item(key = "bottom_spacer") {
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomizerScreenPreview() {
    Project309Theme {
        CustomizerScreen(
            state = CustomizerUiState(),
            onAction = {},
            onNavigateBack = {}
        )
    }
}
