package com.makancompany.project309.ui.tracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makancompany.project309.R
import com.makancompany.project309.ui.tracker.RedeemableReward
import com.makancompany.project309.ui.theme.CaramelOnSecondaryFixed
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryContainer
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun LoyaltyRewardsCard(
    memberName: String,
    memberBeans: Int,
    beansBrewing: Int,
    beansToNextTier: Int,
    goldTierTarget: Int,
    progressPercent: Float,
    rewards: List<RedeemableReward>,
    onRedeemReward: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(1.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(SandSurfaceContainerLow)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Rewards Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(CaramelSecondaryFixed),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_loyalty),
                            contentDescription = "Rewards",
                            modifier = Modifier.size(18.dp),
                            tint = CaramelOnSecondaryFixed
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "$memberName's Bean Rewards",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = EspressoPrimary
                        )
                        Text(
                            text = "+$beansBrewing beans brewing right now!",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = CaramelSecondary
                        )
                    }
                }

                // Total Beans Badge
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "$memberBeans",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = EspressoPrimary
                    )
                    Text(
                        text = "Total Beans",
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant
                    )
                }
            }

            // Progress Dial & Status Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SandSurfaceContainerLowest)
                    .padding(12.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_workspace_premium),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = CaramelSecondary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Gold Tier Unlocks at $goldTierTarget",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = EspressoPrimary
                            )
                        }

                        Text(
                            text = "$beansToNextTier beans away",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium,
                            color = CaramelSecondary
                        )
                    }

                    // Linear Gauge
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(PillShape)
                            .background(SandSurfaceContainerHigh)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progressPercent)
                                .height(10.dp)
                                .clip(PillShape)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            CaramelSecondaryContainer,
                                            CaramelSecondary
                                        )
                                    )
                                )
                        )
                    }

                    // Tier Status Footnote
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Silver Roaster",
                            style = MaterialTheme.typography.labelSmall,
                            color = SandOnSurfaceVariant
                        )
                        Text(
                            text = "Free Artisan Tumbler 🎁",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = EspressoPrimary
                        )
                    }
                }
            }

            // Available Rewards List
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Available Rewards to Redeem",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    color = SandOnSurfaceVariant
                )

                rewards.forEach { reward ->
                    val interactionSource = remember { MutableInteractionSource() }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SandSurfaceContainerLowest)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SandSurfaceContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = reward.iconRes),
                                    contentDescription = reward.title,
                                    modifier = Modifier.size(20.dp),
                                    tint = CaramelSecondary
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = reward.title,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.SemiBold,
                                    color = EspressoPrimary
                                )
                                Text(
                                    text = reward.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SandOnSurfaceVariant
                                )
                            }
                        }

                        // Redeem pill button
                        Box(
                            modifier = Modifier
                                .clip(PillShape)
                                .background(CaramelSecondaryFixed)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = { onRedeemReward(reward.id) }
                                )
                                .padding(horizontal = 14.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${reward.costBeans} Beans",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = CaramelOnSecondaryFixed
                            )
                        }
                    }
                }
            }
        }
    }
}
