package com.makancompany.project309.ui.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makancompany.project309.R
import com.makancompany.project309.ui.checkout.PaymentMethod
import com.makancompany.project309.ui.theme.CaramelOnSecondaryFixed
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh
import com.makancompany.project309.ui.theme.SandSurfaceContainerHighest
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow

@Composable
fun PaymentMethodSection(
    selectedPayment: PaymentMethod,
    onPaymentSelected: (PaymentMethod) -> Unit,
    onAddNewClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Payment Method",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = SandOnSurfaceVariant
            )

            Text(
                text = "Add New",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = CaramelSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = onAddNewClicked)
                    .padding(2.dp)
            )
        }

        // 3 Options
        PaymentMethod.values().forEach { method ->
            val isSelected = method == selectedPayment

            val iconRes = when (method) {
                PaymentMethod.Balance -> R.drawable.ic_wallet
                PaymentMethod.DigitalWallet -> R.drawable.ic_contactless
                PaymentMethod.Card -> R.drawable.ic_credit_card
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isSelected) Modifier.shadow(1.dp, RoundedCornerShape(16.dp))
                        else Modifier
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (isSelected) SandSurfaceContainer
                        else SandSurfaceContainerLow
                    )
                    .clickable { onPaymentSelected(method) }
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Icon Box
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (method == PaymentMethod.Balance && isSelected) EspressoPrimary
                                    else SandSurfaceContainerHighest
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                tint = if (method == PaymentMethod.Balance && isSelected) Color.White else SandOnSurface,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Method Info
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = method.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SandOnSurface
                                )

                                method.badge?.let { badgeText ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(CaramelSecondaryFixed)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = badgeText,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = CaramelOnSecondaryFixed,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }

                            Text(
                                text = method.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = if (method == PaymentMethod.Balance) FontWeight.Medium else FontWeight.Normal,
                                color = if (method == PaymentMethod.Balance) CaramelSecondary else SandOnSurfaceVariant
                            )
                        }
                    }

                    // Radio Check
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) EspressoPrimary
                                else SandSurfaceContainerHigh
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "Selected",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
