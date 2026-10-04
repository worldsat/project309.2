package com.makancompany.project309.ui.checkout.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.checkout.CartItem
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoOutline
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun CartItemsListSection(
    items: List<CartItem>,
    onUpdateQuantity: (String, Int) -> Unit,
    onRemoveItem: (String) -> Unit,
    onAddMoreClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Items (${items.size})",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = SandOnSurfaceVariant
            )
            Text(
                text = "Crafted with care",
                style = MaterialTheme.typography.labelSmall,
                color = CaramelSecondary
            )
        }

        // List of Cart Items
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, RoundedCornerShape(18.dp))
                    .clip(RoundedCornerShape(18.dp))
                    .background(SandSurfaceContainerLow)
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    // Item thumbnail image
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(76.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(SandSurfaceContainer)
                    )

                    // Details Column
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = SandOnSurface
                            )

                            // Delete button
                            IconButton(
                                onClick = { onRemoveItem(item.id) },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = "Remove item",
                                    tint = EspressoOutline,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = SandOnSurfaceVariant,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Price and Stepper
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.formattedUnitPrice,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = EspressoPrimary
                            )

                            // Stepper
                            Row(
                                modifier = Modifier
                                    .clip(PillShape)
                                    .background(SandSurfaceContainerHigh)
                                    .padding(2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val canMinus = item.quantity > 1
                                Box(
                                    modifier = Modifier
                                        .size(26.dp)
                                        .shadow(if (canMinus) 1.dp else 0.dp, CircleShape)
                                        .clip(CircleShape)
                                        .background(
                                            if (canMinus) SandSurfaceContainerLowest
                                            else SandSurfaceContainerHigh
                                        )
                                        .clickable(enabled = canMinus) {
                                            onUpdateQuantity(item.id, -1)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_remove),
                                        contentDescription = "Decrease",
                                        tint = if (canMinus) EspressoPrimary else SandOnSurfaceVariant.copy(alpha = 0.35f),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }

                                Text(
                                    text = "${item.quantity}",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SandOnSurface,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.width(26.dp)
                                )

                                val canPlus = item.quantity < 10
                                Box(
                                    modifier = Modifier
                                        .size(26.dp)
                                        .shadow(if (canPlus) 1.dp else 0.dp, CircleShape)
                                        .clip(CircleShape)
                                        .background(
                                            if (canPlus) SandSurfaceContainerLowest
                                            else SandSurfaceContainerHigh
                                        )
                                        .clickable(enabled = canPlus) {
                                            onUpdateQuantity(item.id, 1)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_add),
                                        contentDescription = "Increase",
                                        tint = if (canPlus) EspressoPrimary else SandOnSurfaceVariant.copy(alpha = 0.35f),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Add More Items Tile
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SandSurfaceContainerLow)
                .clickable(onClick = onAddMoreClicked)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle),
                    contentDescription = null,
                    tint = CaramelSecondary,
                    modifier = Modifier.size(19.dp)
                )
                Text(
                    text = "Add More from the Bakery or Roastery",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = CaramelSecondary
                )
            }
        }
    }
}
