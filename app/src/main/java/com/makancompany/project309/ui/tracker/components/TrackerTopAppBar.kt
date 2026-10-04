package com.makancompany.project309.ui.tracker.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryContainer
import com.makancompany.project309.ui.theme.EspressoOutlineVariant
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandSurface

@Composable
fun TrackerTopAppBar(
    title: String = "Live Order Tracker",
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(56.dp)
            .background(SandSurface)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(44.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

        // BrewCraft Logo
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(CaramelSecondaryContainer.copy(alpha = 0.45f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_brewcraft_logo),
                contentDescription = "BrewCraft Logo",
                tint = CaramelSecondary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = EspressoPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        // More Vert Options
        IconButton(
            onClick = onMoreClick,
            modifier = Modifier.size(44.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "More Options",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(22.dp)
            )
        }

        // Profile Avatar
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .border(1.5.dp, EspressoOutlineVariant, CircleShape)
                .clickable(onClick = onProfileClick)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "User Profile",
                modifier = Modifier.size(34.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
