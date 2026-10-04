package com.makancompany.project309.ui.tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.Project309Theme
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandSurface
import com.makancompany.project309.ui.tracker.components.ActiveOrderStatusCard
import com.makancompany.project309.ui.tracker.components.AtmosphereVisualCard
import com.makancompany.project309.ui.tracker.components.DigitalPickupPassCard
import com.makancompany.project309.ui.tracker.components.LoyaltyRewardsCard
import com.makancompany.project309.ui.tracker.components.TrackerActionButtons
import com.makancompany.project309.ui.tracker.components.TrackerHeaderBanner
import com.makancompany.project309.ui.tracker.components.TrackerTopAppBar

@Composable
fun TrackerRoute(
    viewModel: TrackerViewModel = remember { TrackerViewModel() },
    onNavigateBack: () -> Unit,
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
            viewModel.onAction(TrackerAction.DismissMessage)
        }
    }

    TrackerScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateBack = onNavigateBack,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun TrackerScreen(
    state: TrackerUiState,
    onAction: (TrackerAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        containerColor = SandSurface,
        topBar = {
            TrackerTopAppBar(
                title = "Live Order Tracker",
                onBackClick = onNavigateBack,
                onMoreClick = { onAction(TrackerAction.MoreOptionsClicked) },
                onProfileClick = {}
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
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
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Live Pickup Tracker Banner
            item(key = "header_banner") {
                TrackerHeaderBanner()
            }

            // Active Order Status Card with Stepper & Barista Note
            item(key = "order_status_card") {
                ActiveOrderStatusCard(
                    orderNumber = state.orderNumber,
                    pickupBar = state.pickupBar,
                    remainingMinutes = state.estimatedMinutesRemaining,
                    baristaName = state.baristaName,
                    baristaNote = state.baristaNote
                )
            }

            // Digital Pickup Pass & Store QR Card
            item(key = "pickup_pass_card") {
                DigitalPickupPassCard(
                    productName = state.productName,
                    productDetails = state.productDetails,
                    storeName = state.storeName,
                    storeAddress = state.storeAddress,
                    onGetDirections = { onAction(TrackerAction.GetDirectionsClicked) },
                    onCallStore = { onAction(TrackerAction.CallStoreClicked) }
                )
            }

            // Loyalty & Rewards Progress Card
            item(key = "loyalty_rewards_card") {
                LoyaltyRewardsCard(
                    memberName = state.memberName,
                    memberBeans = state.memberBeans,
                    beansBrewing = state.beansBrewing,
                    beansToNextTier = state.beansToNextTier,
                    goldTierTarget = state.goldTierTarget,
                    progressPercent = state.progressPercent,
                    rewards = state.rewards,
                    onRedeemReward = { rewardId ->
                        onAction(TrackerAction.RedeemRewardClicked(rewardId))
                    }
                )
            }

            // Store Atmosphere Sensory Visual Card
            item(key = "atmosphere_visual") {
                AtmosphereVisualCard()
            }

            // Bottom Action Buttons (Download Receipt & Help)
            item(key = "action_buttons") {
                TrackerActionButtons(
                    isGeneratingReceipt = state.isGeneratingReceipt,
                    receiptSaved = state.receiptSaved,
                    onDownloadReceipt = { onAction(TrackerAction.DownloadReceiptClicked) },
                    onNeedHelp = { onAction(TrackerAction.NeedHelpClicked) }
                )
            }

            item(key = "bottom_space") {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun TrackerScreenPreview() {
    Project309Theme {
        TrackerScreen(
            state = TrackerUiState(),
            onAction = {},
            onNavigateBack = {}
        )
    }
}
