package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun GreetingSection(
    greeting: String,
    userTier: String,
    readyEstimate: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Main greeting row with Tier badge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = greeting,
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
                    text = userTier,
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

            // Parse "~10 mins" for bold highlight
            val statusText = if (readyEstimate.contains("~10 mins")) {
                val parts = readyEstimate.split("~10 mins")
                buildAnnotatedString {
                    append(parts.getOrElse(0) { "" })
                    withStyle(
                        style = SpanStyle(
                            color = EspressoPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("~10 mins")
                    }
                    append(parts.getOrElse(1) { "" })
                }
            } else {
                buildAnnotatedString {
                    append(readyEstimate)
                }
            }

            Text(
                text = statusText,
                style = MaterialTheme.typography.bodySmall,
                color = SandOnSurfaceVariant
            )
        }
    }
}
