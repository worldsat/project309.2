package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.GoldTertiaryFixed
import com.makancompany.project309.ui.theme.MochaTertiaryContainer
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow

@Composable
fun BrewCraftClubCard(
    currentBeans: Int,
    maxBeans: Int,
    nextReward: String,
    onViewPerksClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = (currentBeans.toFloat() / maxBeans.toFloat()).coerceIn(0f, 1f)
    val remainingBeans = (maxBeans - currentBeans).coerceAtLeast(0)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(SandSurfaceContainerLow)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Top row: Badge + Title / Subtitle + Count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Circular coffee cup badge
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(GoldTertiaryFixed),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cafe_badge),
                            contentDescription = "Club Badge",
                            tint = MochaTertiaryContainer,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "BrewCraft Club",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = EspressoPrimary
                        )
                        Text(
                            text = "$remainingBeans beans until your free cup",
                            style = MaterialTheme.typography.bodySmall,
                            color = SandOnSurfaceVariant
                        )
                    }
                }

                // Bean ratio indicator: 140 / 200
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$currentBeans",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = CaramelSecondary
                    )
                    Text(
                        text = " / $maxBeans",
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }
            }

            // Progress bar indicator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(9999.dp))
                    .background(SandSurfaceContainerHigh)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(9999.dp))
                        .background(CaramelSecondary)
                )
            }

            // Bottom row: Gift next reward & View perks link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_redeem),
                        contentDescription = null,
                        tint = CaramelSecondary,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "Next reward: $nextReward",
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant
                    )
                }

                Text(
                    text = "View perks →",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = CaramelSecondary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(onClick = onViewPerksClick)
                )
            }
        }
    }
}
