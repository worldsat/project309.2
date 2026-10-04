package com.makancompany.project309.ui.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.makancompany.project309.ui.checkout.FulfillmentMode
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh

@Composable
fun FulfillmentToggle(
    selectedMode: FulfillmentMode,
    onModeSelected: (FulfillmentMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(1.dp, PillShape)
            .clip(PillShape)
            .background(SandSurfaceContainerHigh)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Pickup
            val isPickup = selectedMode == FulfillmentMode.Pickup
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(PillShape)
                    .then(
                        if (isPickup) Modifier
                            .shadow(2.dp, PillShape)
                            .background(EspressoPrimary)
                        else Modifier
                    )
                    .clickable { onModeSelected(FulfillmentMode.Pickup) }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_storefront),
                            contentDescription = "Pickup",
                            tint = if (isPickup) Color.White else SandOnSurfaceVariant,
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = "Pickup",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = if (isPickup) FontWeight.Bold else FontWeight.Medium,
                            color = if (isPickup) Color.White else SandOnSurfaceVariant
                        )
                    }
                    Text(
                        text = "10-15 mins",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isPickup) Color.White.copy(alpha = 0.85f) else SandOnSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            }

            // Fast Delivery
            val isDelivery = selectedMode == FulfillmentMode.Delivery
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(PillShape)
                    .then(
                        if (isDelivery) Modifier
                            .shadow(2.dp, PillShape)
                            .background(EspressoPrimary)
                        else Modifier
                    )
                    .clickable { onModeSelected(FulfillmentMode.Delivery) }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_moped),
                            contentDescription = "Fast Delivery",
                            tint = if (isDelivery) Color.White else SandOnSurfaceVariant,
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = "Fast Delivery",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = if (isDelivery) FontWeight.Bold else FontWeight.Medium,
                            color = if (isDelivery) Color.White else SandOnSurfaceVariant
                        )
                    }
                    Text(
                        text = "25-30 mins",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isDelivery) Color.White.copy(alpha = 0.85f) else SandOnSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
