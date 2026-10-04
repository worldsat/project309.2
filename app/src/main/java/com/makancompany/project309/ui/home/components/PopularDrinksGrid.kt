package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.home.DrinkItem
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary

@Composable
fun PopularDrinksSection(
    drinks: List<DrinkItem>,
    favorites: Set<String>,
    onFavoriteToggle: (String) -> Unit,
    onAddToCart: (DrinkItem) -> Unit,
    onDrinkClick: (String) -> Unit,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_fire),
                    contentDescription = null,
                    tint = CaramelSecondary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Popular Drinks",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = EspressoPrimary
                )
            }

            Text(
                text = "See all (18)",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                color = CaramelSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = onSeeAllClick)
                    .padding(4.dp)
            )
        }

        // Two-Column Grid items
        val chunkedDrinks = drinks.chunked(2)
        chunkedDrinks.forEach { pair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // First card
                val drink1 = pair[0]
                BrewProductCard(
                    drink = drink1,
                    isFavorite = favorites.contains(drink1.id),
                    onFavoriteClick = { onFavoriteToggle(drink1.id) },
                    onAddToCartClick = { onAddToCart(drink1) },
                    onCardClick = { onDrinkClick(drink1.id) },
                    modifier = Modifier.weight(1f)
                )

                // Second card if exists
                if (pair.size > 1) {
                    val drink2 = pair[1]
                    BrewProductCard(
                        drink = drink2,
                        isFavorite = favorites.contains(drink2.id),
                        onFavoriteClick = { onFavoriteToggle(drink2.id) },
                        onAddToCartClick = { onAddToCart(drink2) },
                        onCardClick = { onDrinkClick(drink2.id) },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
