package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makancompany.project309.R
import com.makancompany.project309.ui.home.DrinkItem
import com.makancompany.project309.ui.theme.CaramelSecondaryContainer
import com.makancompany.project309.ui.theme.ErrorColor
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurface
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun BrewProductCard(
    drink: DrinkItem,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(1.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(SandSurfaceContainerLow)
            .clickable(onClick = onCardClick)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Product Image Container with Favorite Heart Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SandSurfaceContainer)
            ) {
                Image(
                    painter = painterResource(id = drink.imageRes),
                    contentDescription = drink.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Favorite Heart Toggle Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(SandSurfaceContainerLowest.copy(alpha = 0.85f))
                        .clickable(onClick = onFavoriteClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            if (isFavorite) R.drawable.ic_favorite_filled
                            else R.drawable.ic_favorite_border
                        ),
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) ErrorColor else SandOnSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Rating + Name + Description
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Rating row: Star + 4.9 + (1.2k)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star_filled),
                        contentDescription = null,
                        tint = CaramelSecondaryContainer,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "${drink.rating}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = SandOnSurface
                    )
                    Text(
                        text = "(${drink.reviewCount})",
                        style = MaterialTheme.typography.labelSmall,
                        color = SandOnSurfaceVariant
                    )
                }

                // Drink Name
                Text(
                    text = drink.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = EspressoPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Drink Description
                Text(
                    text = drink.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = SandOnSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Bottom row: Price & Add button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = drink.price,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = EspressoPrimary
            )

            // Add (+) Button: Circular Espresso Primary with White plus icon
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .shadow(1.dp, CircleShape)
                    .clip(CircleShape)
                    .background(EspressoPrimary)
                    .clickable(onClick = onAddToCartClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add to Cart",
                    tint = SandSurfaceContainerLowest,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
