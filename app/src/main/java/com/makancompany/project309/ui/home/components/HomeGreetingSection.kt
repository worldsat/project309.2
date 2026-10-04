package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelOnSecondaryContainer
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryContainer
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant

@Composable
fun HomeGreetingSection(
    userName: String = "Alex",
    tierName: String = "Tier Gold",
    readyTime: String = "~10 mins",
    branchName: String = "Downtown Roastery",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Main greeting row with Tier badge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Good morning, $userName ☕",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = EspressoPrimary
            )

            // Tier Gold Pill
            Row(
                modifier = Modifier
                    .background(
                        color = CaramelSecondaryContainer,
                        shape = RoundedCornerShape(9999.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_bolt),
                    contentDescription = null,
                    tint = CaramelOnSecondaryContainer,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = tierName,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = CaramelOnSecondaryContainer
                )
            }
        }

        // Subtitle status row: "Ready in ~10 mins at Downtown Roastery"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_schedule),
                contentDescription = null,
                tint = CaramelSecondary,
                modifier = Modifier.size(16.dp)
            )

            val statusText = buildAnnotatedString {
                append("Ready in ")
                withStyle(
                    style = SpanStyle(
                        color = EspressoPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(readyTime)
                }
                append(" at $branchName")
            }

            Text(
                text = statusText,
                style = MaterialTheme.typography.bodySmall,
                color = SandOnSurfaceVariant
            )
        }
    }
}
