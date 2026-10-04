package com.makancompany.project309.ui.tracker.components

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.PillShape
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant

@Composable
fun TrackerActionButtons(
    isGeneratingReceipt: Boolean,
    receiptSaved: Boolean,
    onDownloadReceipt: () -> Unit,
    onNeedHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        val interactionSource1 = remember { MutableInteractionSource() }
        val interactionSource2 = remember { MutableInteractionSource() }

        // Download Order Receipt Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(PillShape)
                .clickable(
                    interactionSource = interactionSource1,
                    indication = null,
                    enabled = !isGeneratingReceipt,
                    onClick = onDownloadReceipt
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (isGeneratingReceipt) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = EspressoPrimary,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Generating Receipt...",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = EspressoPrimary
                    )
                } else if (receiptSaved) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = CaramelSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Receipt Saved! (PDF)",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = CaramelSecondary
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_receipt_long),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = EspressoPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Download Order Receipt (PDF)",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = EspressoPrimary
                    )
                }
            }
        }

        // Need Help or Report an Issue Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .clip(PillShape)
                .clickable(
                    interactionSource = interactionSource2,
                    indication = null,
                    onClick = onNeedHelp
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_help_outline),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = SandOnSurfaceVariant
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Need Help or Report an Issue?",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    color = SandOnSurfaceVariant
                )
            }
        }
    }
}
