package com.makancompany.project309.ui.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.checkout.CheckoutUiState
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoOutline
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerHighest
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow

@Composable
fun PriceBreakdownCard(
    state: CheckoutUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(1.dp, RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .background(SandSurfaceContainerLow)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header: Price Breakdown & Count Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Price Breakdown",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SandOnSurface
                )

                Box(
                    modifier = Modifier
                        .clip(PillShape)
                        .background(SandSurfaceContainer)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "${state.items.sumOf { it.quantity }} items",
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Subtotal
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Subtotal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SandOnSurfaceVariant
                )
                Text(
                    text = state.formattedSubtotal,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = SandOnSurface
                )
            }

            // Fee
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = state.fulfillmentMode.feeLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = SandOnSurfaceVariant
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = null,
                        tint = EspressoOutline,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Text(
                    text = state.formattedFee,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = SandOnSurface
                )
            }

            // Promo Discount
            if (state.voucherApplied && state.discount > 0) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Promo Discount (BREWFIRST)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = CaramelSecondary
                    )
                    Text(
                        text = state.formattedDiscount,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = CaramelSecondary
                    )
                }
            }

            // Estimated Tax
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Estimated Tax (8.5%)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SandOnSurfaceVariant
                )
                Text(
                    text = state.formattedTax,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = SandOnSurface
                )
            }

            // Divider
            HorizontalDivider(
                color = SandSurfaceContainerHighest,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Total Amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "Total Amount",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = SandOnSurface
                    )
                    Text(
                        text = "Includes all local taxes",
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant
                    )
                }

                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "USD",
                        modifier = Modifier.alignByBaseline(),
                        style = MaterialTheme.typography.labelMedium,
                        color = EspressoOutline
                    )
                    Text(
                        text = state.formattedTotal,
                        modifier = Modifier.alignByBaseline(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = EspressoPrimary
                    )
                }
            }
        }
    }
}
