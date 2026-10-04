package com.makancompany.project309.ui.customizer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.makancompany.project309.ui.customizer.CupSize
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryFixed
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow

@Composable
fun CupSizeSelector(
    selectedSize: CupSize,
    onSizeSelected: (CupSize) -> Unit,
    onVolumeGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cup Size",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = EspressoPrimary
            )

            Text(
                text = "Volume Guide",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = CaramelSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = onVolumeGuideClick)
                    .padding(2.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CupSize.values().forEach { size ->
                val isSelected = size == selectedSize

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .then(
                            if (isSelected) Modifier.shadow(3.dp, RoundedCornerShape(16.dp))
                            else Modifier
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) EspressoPrimary else SandSurfaceContainerLow)
                        .clickable { onSizeSelected(size) }
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_local_cafe),
                        contentDescription = size.title,
                        tint = if (isSelected) CaramelSecondaryFixed else SandOnSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = size.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else SandOnSurface
                    )

                    Text(
                        text = size.volume,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isSelected) Color(0xFFD4C3BD) else SandOnSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = size.surchargeLabel,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) CaramelSecondaryFixed else SandOnSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
