package com.makancompany.project309.ui.tracker.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelOnSecondaryFixed
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun DigitalPickupPassCard(
    productName: String,
    productDetails: String,
    storeName: String,
    storeAddress: String,
    onGetDirections: () -> Unit,
    onCallStore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(1.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(SandSurfaceContainerLowest)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_qr_code),
                        contentDescription = "QR Pass",
                        modifier = Modifier.size(22.dp),
                        tint = CaramelSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Digital Pickup Pass",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = EspressoPrimary
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(PillShape)
                        .background(CaramelSecondaryFixed.copy(alpha = 0.5f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Scan at Counter",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium,
                        color = CaramelSecondary
                    )
                }
            }

            // QR Code Scan Module
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SandSurfaceContainerLow)
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // QR Code Graphic in White Tile
                    Box(
                        modifier = Modifier
                            .size(108.dp)
                            .shadow(1.dp, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(SandSurfaceContainerLowest)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        QrCodeCanvas(
                            modifier = Modifier.size(92.dp),
                            qrColor = EspressoPrimary,
                            bgColor = Color(0xFFFAF3ED)
                        )
                    }

                    // Pass Summary Info
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = productName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = EspressoPrimary
                        )

                        Text(
                            text = productDetails,
                            style = MaterialTheme.typography.bodySmall,
                            color = SandOnSurfaceVariant,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        // Auto-verified tag
                        Box(
                            modifier = Modifier
                                .clip(PillShape)
                                .background(SandSurfaceContainer)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_verified),
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp),
                                    tint = CaramelSecondary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Auto-verified at counter",
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.5.sp),
                                    color = SandOnSurface
                                )
                            }
                        }
                    }
                }
            }

            // Store Location & Tonal Assist Chips
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location_pin),
                        contentDescription = "Store location",
                        modifier = Modifier.size(20.dp),
                        tint = CaramelSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = storeName,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = EspressoPrimary
                        )
                        Text(
                            text = storeAddress,
                            style = MaterialTheme.typography.bodySmall,
                            color = SandOnSurfaceVariant
                        )
                    }
                }

                // Assist Chips Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val interactionSource1 = remember { MutableInteractionSource() }
                    val interactionSource2 = remember { MutableInteractionSource() }

                    // Get Directions
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp)
                            .clip(PillShape)
                            .background(CaramelSecondaryFixed)
                            .clickable(
                                interactionSource = interactionSource1,
                                indication = null,
                                onClick = onGetDirections
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_directions),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = CaramelOnSecondaryFixed
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Get Directions",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = CaramelOnSecondaryFixed
                            )
                        }
                    }

                    // Call Store
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp)
                            .clip(PillShape)
                            .background(SandSurfaceContainer)
                            .clickable(
                                interactionSource = interactionSource2,
                                indication = null,
                                onClick = onCallStore
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_call),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = EspressoPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Call Store",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = EspressoPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QrCodeCanvas(
    modifier: Modifier = Modifier,
    qrColor: Color = EspressoPrimary,
    bgColor: Color = Color(0xFFFAF3ED)
) {
    Canvas(modifier = modifier) {
        val scale = size.width / 100f
        fun s(v: Float) = v * scale

        // Helper to draw rounded rect from 100x100 coord system
        fun drawR(x: Float, y: Float, w: Float, h: Float, rx: Float, color: Color) {
            drawRoundRect(
                color = color,
                topLeft = Offset(s(x), s(y)),
                size = Size(s(w), s(h)),
                cornerRadius = CornerRadius(s(rx), s(rx))
            )
        }

        // Top-Left Finder
        drawR(0f, 0f, 30f, 30f, 4f, qrColor)
        drawR(5f, 5f, 20f, 20f, 2f, bgColor)
        drawR(9f, 9f, 12f, 12f, 1f, qrColor)

        // Top-Right Finder
        drawR(70f, 0f, 30f, 30f, 4f, qrColor)
        drawR(75f, 5f, 20f, 20f, 2f, bgColor)
        drawR(79f, 9f, 12f, 12f, 1f, qrColor)

        // Bottom-Left Finder
        drawR(0f, 70f, 30f, 30f, 4f, qrColor)
        drawR(5f, 75f, 20f, 20f, 2f, bgColor)
        drawR(9f, 79f, 12f, 12f, 1f, qrColor)

        // Data Blocks
        drawR(36f, 8f, 6f, 6f, 1f, qrColor)
        drawR(46f, 8f, 6f, 6f, 1f, qrColor)
        drawR(56f, 16f, 6f, 6f, 1f, qrColor)
        drawR(36f, 24f, 6f, 6f, 1f, qrColor)
        drawR(46f, 24f, 6f, 6f, 1f, qrColor)
        drawR(10f, 38f, 6f, 6f, 1f, qrColor)
        drawR(22f, 44f, 6f, 6f, 1f, qrColor)
        drawR(36f, 38f, 8f, 8f, 2f, qrColor)
        drawR(50f, 38f, 6f, 6f, 1f, qrColor)
        drawR(62f, 38f, 6f, 6f, 1f, qrColor)
        drawR(74f, 38f, 6f, 6f, 1f, qrColor)
        drawR(86f, 44f, 6f, 6f, 1f, qrColor)
        drawR(36f, 52f, 6f, 6f, 1f, qrColor)
        drawR(48f, 52f, 8f, 8f, 2f, qrColor)
        drawR(64f, 52f, 6f, 6f, 1f, qrColor)
        drawR(76f, 58f, 6f, 6f, 1f, qrColor)
        drawR(12f, 58f, 6f, 6f, 1f, qrColor)
        drawR(36f, 66f, 6f, 6f, 1f, qrColor)
        drawR(48f, 66f, 6f, 6f, 1f, qrColor)
        drawR(62f, 66f, 6f, 6f, 1f, qrColor)
        drawR(74f, 74f, 6f, 6f, 1f, qrColor)
        drawR(84f, 74f, 6f, 6f, 1f, qrColor)
        drawR(36f, 80f, 8f, 8f, 2f, qrColor)
        drawR(52f, 80f, 6f, 6f, 1f, qrColor)
        drawR(64f, 80f, 6f, 6f, 1f, qrColor)
        drawR(76f, 86f, 6f, 6f, 1f, qrColor)
        drawR(86f, 86f, 6f, 6f, 1f, qrColor)
    }
}
