package com.makancompany.project309.ui.tracker.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelOnSecondary
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.EspressoOnPrimary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh
import com.makancompany.project309.ui.theme.SandSurfaceContainerHighest
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow

@Composable
fun ActiveOrderStatusCard(
    orderNumber: String,
    pickupBar: String,
    remainingMinutes: Int,
    baristaName: String,
    baristaNote: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(2.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(SandSurfaceContainerLow)
    ) {
        // Decorative warm ambient glow
        Box(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.TopEnd)
                .offset(x = 30.dp, y = (-30).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            CaramelSecondaryFixed.copy(alpha = 0.45f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Details Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "ORDER $orderNumber",
                        style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                        fontWeight = FontWeight.Medium,
                        color = SandOnSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_timer),
                            contentDescription = "Timer",
                            modifier = Modifier.size(18.dp),
                            tint = CaramelSecondary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "$remainingMinutes mins remaining",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = EspressoPrimary
                        )
                    }
                }

                // Pickup Bar Pill
                Box(
                    modifier = Modifier
                        .clip(PillShape)
                        .background(SandSurfaceContainer)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = pickupBar,
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // M3 Progress Stepper
            OrderProgressStepper()

            // Barista Warm Note & Portrait
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SandSurfaceContainer)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(46.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = baristaName,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    // Mini barista badge
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(CaramelSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(10.dp),
                            tint = CaramelOnSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = baristaName,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = EspressoPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = baristaNote,
                        style = MaterialTheme.typography.bodySmall,
                        color = SandOnSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderProgressStepper() {
    val infiniteTransition = rememberInfiniteTransition(label = "ActivePulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseScale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // Connecting Progress Bar Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = 18.dp)
                .height(4.dp)
                .clip(PillShape)
                .background(SandSurfaceContainerHighest)
        ) {
            // Active portion (~42%)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.42f)
                    .height(4.dp)
                    .clip(PillShape)
                    .background(CaramelSecondary)
            )
        }

        // Stepper Nodes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Step 1: Received (Completed)
            StepNode(
                iconRes = R.drawable.ic_check,
                title = "Order\nReceived",
                containerColor = EspressoPrimary,
                contentColor = EspressoOnPrimary,
                textColor = EspressoPrimary,
                isBold = false
            )

            // Step 2: Brewing (Active)
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                // Pulse ring behind active node
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .scale(pulseScale)
                        .clip(CircleShape)
                        .background(CaramelSecondaryFixed.copy(alpha = 0.5f))
                )

                StepNode(
                    iconRes = R.drawable.ic_coffee,
                    title = "Brewing &\nCrafting",
                    containerColor = CaramelSecondary,
                    contentColor = CaramelOnSecondary,
                    textColor = CaramelSecondary,
                    isBold = true
                )
            }

            // Step 3: Ready (Upcoming)
            StepNode(
                iconRes = R.drawable.ic_storefront,
                title = "Ready for\nPickup",
                containerColor = SandSurfaceContainerHigh,
                contentColor = SandOnSurfaceVariant,
                textColor = SandOnSurfaceVariant,
                isBold = false
            )

            // Step 4: Enjoy (Upcoming)
            StepNode(
                iconRes = R.drawable.ic_sentiment_satisfied,
                title = "Enjoy!",
                containerColor = SandSurfaceContainerHigh,
                contentColor = SandOnSurfaceVariant,
                textColor = SandOnSurfaceVariant,
                isBold = false
            )
        }
    }
}

@Composable
private fun StepNode(
    iconRes: Int,
    title: String,
    containerColor: Color,
    contentColor: Color,
    textColor: Color,
    isBold: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(72.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .shadow(if (isBold) 2.dp else 0.dp, CircleShape)
                .clip(CircleShape)
                .background(containerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = contentColor
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            ),
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}
