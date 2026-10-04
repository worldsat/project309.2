package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelOnSecondary
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer

@Composable
fun CategoryChipRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore Categories",
                style = MaterialTheme.typography.titleMedium,
                color = EspressoPrimary
            )
            Text(
                text = "Scroll to view",
                style = MaterialTheme.typography.labelSmall,
                color = SandOnSurfaceVariant
            )
        }

        // Horizontal Chips Row
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory
                val chipBg = if (isSelected) CaramelSecondary else SandSurfaceContainer
                val contentColor = if (isSelected) CaramelOnSecondary else SandOnSurfaceVariant

                val iconRes = when (category.lowercase()) {
                    "all" -> R.drawable.ic_check
                    "espresso" -> R.drawable.ic_cat_espresso
                    "cold brew" -> R.drawable.ic_cat_coldbrew
                    "pourover" -> R.drawable.ic_cat_pourover
                    "signature lattes" -> R.drawable.ic_cat_signature
                    "pastries" -> R.drawable.ic_cat_pastries
                    else -> R.drawable.ic_cat_espresso
                }

                Row(
                    modifier = Modifier
                        .height(36.dp)
                        .then(
                            if (isSelected) Modifier.shadow(2.dp, RoundedCornerShape(9999.dp))
                            else Modifier
                        )
                        .clip(RoundedCornerShape(9999.dp))
                        .background(chipBg)
                        .clickable { onCategorySelected(category) }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = category,
                        tint = contentColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = category,
                        style = MaterialTheme.typography.labelMedium,
                        color = contentColor
                    )
                }
            }
        }
    }
}
