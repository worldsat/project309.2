package com.makancompany.project309.ui.checkout

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
import com.makancompany.project309.ui.checkout.components.CartItemsListSection
import com.makancompany.project309.ui.checkout.components.CheckoutHeaderSection
import com.makancompany.project309.ui.checkout.components.CheckoutStickyBottomBar
import com.makancompany.project309.ui.checkout.components.CheckoutTopAppBar
import com.makancompany.project309.ui.checkout.components.FreshlyGroundNote
import com.makancompany.project309.ui.checkout.components.FulfillmentToggle
import com.makancompany.project309.ui.checkout.components.PaymentMethodSection
import com.makancompany.project309.ui.checkout.components.PriceBreakdownCard
import com.makancompany.project309.ui.checkout.components.PromotionsSection
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.Project309Theme
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandSurface

@Composable
fun CheckoutRoute(
    viewModel: CheckoutViewModel = remember { CheckoutViewModel() },
    onNavigateBack: () -> Unit,
    onNavigateToTracker: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.userMessage) {
        state.userMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            viewModel.onAction(CheckoutAction.DismissMessage)
        }
    }

    CheckoutScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateBack = onNavigateBack,
        onNavigateToTracker = onNavigateToTracker,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun CheckoutScreen(
    state: CheckoutUiState,
    onAction: (CheckoutAction) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToTracker: () -> Unit = {},
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        containerColor = SandSurface,
        topBar = {
            CheckoutTopAppBar(
                title = "Checkout",
                onBackClick = onNavigateBack,
                onMoreClick = { onAction(CheckoutAction.MoreOptionsClicked) },
                onProfileClick = {}
            )
        },
        bottomBar = {
            CheckoutStickyBottomBar(
                totalAmount = state.formattedTotal,
                fulfillmentMode = state.fulfillmentMode,
                isSubmitting = state.isSubmittingOrder,
                orderSubmitted = state.orderSubmitted,
                onPlaceOrderClicked = {
                    onAction(CheckoutAction.PlaceOrder)
                    onNavigateToTracker()
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
                            modifier = Modifier.size(18.dp),
                            tint = CaramelSecondaryFixed
                        )
                        Text(
                            text = data.visuals.message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 16.dp)
        ) {
            // Header Review Order & Downtown Roastery Badge
            item(key = "header_section") {
                CheckoutHeaderSection(branchName = state.branchName)
            }

            // Pickup vs Fast Delivery Segmented Toggle
            item(key = "fulfillment_toggle") {
                FulfillmentToggle(
                    selectedMode = state.fulfillmentMode,
                    onModeSelected = { mode ->
                        onAction(CheckoutAction.SelectFulfillment(mode))
                    }
                )
            }

            // Cart Items Section (with Item Cards & "Add More" tile)
            item(key = "cart_items") {
                CartItemsListSection(
                    items = state.items,
                    onUpdateQuantity = { itemId, delta ->
                        onAction(CheckoutAction.UpdateQuantity(itemId, delta))
                    },
                    onRemoveItem = { itemId ->
                        onAction(CheckoutAction.RemoveItem(itemId))
                    },
                    onAddMoreClicked = {
                        onAction(CheckoutAction.AddMoreItemsClicked)
                    }
                )
            }

            // Promotions & Benefits Section (Input & Applied Voucher Banner)
            item(key = "promotions") {
                PromotionsSection(
                    voucherCode = state.voucherCodeInput,
                    voucherApplied = state.voucherApplied,
                    onVoucherCodeChange = { code ->
                        onAction(CheckoutAction.UpdateVoucherInput(code))
                    },
                    onApplyVoucher = {
                        onAction(CheckoutAction.ApplyVoucher)
                    },
                    onRemoveVoucher = {
                        onAction(CheckoutAction.RemoveVoucher)
                    }
                )
            }

            // Price Breakdown Card
            item(key = "price_breakdown") {
                PriceBreakdownCard(state = state)
            }

            // Payment Method Selector
            item(key = "payment_method") {
                PaymentMethodSection(
                    selectedPayment = state.selectedPayment,
                    onPaymentSelected = { payment ->
                        onAction(CheckoutAction.SelectPayment(payment))
                    },
                    onAddNewClicked = {
                        onAction(CheckoutAction.AddPaymentMethodClicked)
                    }
                )
            }

            // Extra Coffee Roaster Promise & Delight Note
            item(key = "freshly_ground_note") {
                FreshlyGroundNote(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }

            // Extra bottom breathing room so content scrolls comfortably above the sticky bottom bar
            item(key = "bottom_spacer") {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun CheckoutScreenPreview() {
    Project309Theme {
        CheckoutScreen(
            state = CheckoutUiState(),
            onAction = {},
            onNavigateBack = {}
        )
    }
}
