package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerHigh

@Composable
fun HomeSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onVoiceClick: () -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(9999.dp))
            .background(SandSurfaceContainer)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search icon
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "Search",
            tint = SandOnSurfaceVariant,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Search text input / placeholder
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (query.isEmpty()) {
                Text(
                    text = "Search coffee, roast, pastries...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SandOnSurfaceVariant
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = SandOnSurface
                ),
                cursorBrush = SolidColor(EspressoPrimary),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Mic icon button
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .clickable(onClick = onVoiceClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_mic),
                contentDescription = "Voice Search",
                tint = SandOnSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Filter / Tune icon button
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(SandSurfaceContainerHigh)
                .clickable(onClick = onFilterClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_tune),
                contentDescription = "Filters",
                tint = EspressoPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
