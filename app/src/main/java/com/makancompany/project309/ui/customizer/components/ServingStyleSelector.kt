package com.makancompany.project309.ui.customizer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.makancompany.project309.R
import com.makancompany.project309.ui.customizer.ServingStyle
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer

@Composable
fun ServingStyleSelector(
    selectedStyle: ServingStyle,
    onStyleSelected: (ServingStyle) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = "Serving Style",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = EspressoPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Pill Segmented container
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(PillShape)
                .background(SandSurfaceContainer)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Iced Button
            val isIced = selectedStyle == ServingStyle.Iced
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .then(
                        if (isIced) Modifier
                            .shadow(2.dp, PillShape)
                            .clip(PillShape)
                            .background(CaramelSecondary)
                        else Modifier
                            .clip(PillShape)
                    )
                    .clickable { onStyleSelected(ServingStyle.Iced) },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ac_unit),
                        contentDescription = "Iced",
                        tint = if (isIced) Color.White else SandOnSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Iced",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = if (isIced) FontWeight.Bold else FontWeight.Medium,
                        color = if (isIced) Color.White else SandOnSurfaceVariant
                    )
                }
            }

            // Hot Button
            val isHot = selectedStyle == ServingStyle.Hot
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .then(
                        if (isHot) Modifier
                            .shadow(2.dp, PillShape)
                            .clip(PillShape)
                            .background(CaramelSecondary)
                        else Modifier
                            .clip(PillShape)
                    )
                    .clickable { onStyleSelected(ServingStyle.Hot) },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_hot_steam),
                        contentDescription = "Hot",
                        tint = if (isHot) Color.White else SandOnSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Hot",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = if (isHot) FontWeight.Bold else FontWeight.Medium,
                        color = if (isHot) Color.White else SandOnSurfaceVariant
                    )
                }
            }
        }
    }
}
