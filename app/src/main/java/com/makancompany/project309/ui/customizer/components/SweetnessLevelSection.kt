package com.makancompany.project309.ui.customizer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.ui.customizer.SweetnessLevel
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SweetnessLevelSection(
    selectedSweetness: SweetnessLevel,
    onSweetnessSelected: (SweetnessLevel) -> Unit,
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
                text = "Sweetness Level",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = EspressoPrimary
            )
            Text(
                text = "Madagascar Vanilla",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
                color = CaramelSecondary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            SweetnessLevel.values().forEach { level ->
                val isSelected = level == selectedSweetness

                Box(
                    modifier = Modifier
                        .clip(PillShape)
                        .then(
                            if (isSelected) Modifier
                                .shadow(2.dp, PillShape)
                                .background(CaramelSecondary)
                            else Modifier.background(SandSurfaceContainer)
                        )
                        .clickable { onSweetnessSelected(level) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = level.title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color.White else SandOnSurfaceVariant
                    )
                }
            }
        }
    }
}
