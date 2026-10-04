package com.makancompany.project309.ui.customizer.components

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelSecondaryContainer
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun EspressoShotsCard(
    shots: Int,
    shotDescription: String,
    onAdjustShots: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(1.dp, RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .background(SandSurfaceContainerLow)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left icon + titles
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CaramelSecondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_coffee_maker),
                        contentDescription = null,
                        tint = EspressoPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Espresso Shots",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = EspressoPrimary
                    )
                    Text(
                        text = shotDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = SandOnSurfaceVariant
                    )
                }
            }

            // Right Stepper
            Row(
                modifier = Modifier
                    .clip(PillShape)
                    .background(SandSurfaceContainerHigh)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Minus button
                val canMinus = shots > 1
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(if (canMinus) 1.dp else 0.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            if (canMinus) SandSurfaceContainerLowest
                            else SandSurfaceContainerHigh
                        )
                        .clickable(enabled = canMinus) { onAdjustShots(-1) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "Decrease Shots",
                        tint = if (canMinus) EspressoPrimary else SandOnSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(16.dp)
                    )
                }

                Text(
                    text = "$shots ${if (shots == 1) "Shot" else "Shots"}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = EspressoPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                // Plus button
                val canPlus = shots < 4
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(if (canPlus) 1.dp else 0.dp, CircleShape)
                        .clip(CircleShape)
                        .background(
                            if (canPlus) SandSurfaceContainerLowest
                            else SandSurfaceContainerHigh
                        )
                        .clickable(enabled = canPlus) { onAdjustShots(1) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Increase Shots",
                        tint = if (canPlus) EspressoPrimary else SandOnSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
