package com.makancompany.project309.ui.customizer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandSurfaceContainerLow
import com.makancompany.project309.ui.theme.SandSurfaceContainerLowest

@Composable
fun ProductHeroSection(
    @DrawableRes imageRes: Int,
    calories: String,
    productName: String,
    tag1: String = "100% Arabica",
    tag2: String = "Medium Roast",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(SandSurfaceContainerLow)
            .aspectRatio(4f / 3f)
    ) {
        // Main Product Image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = productName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Ambient vignette gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x22000000),
                            Color.Transparent,
                            Color(0x992B1409)
                        )
                    )
                )
        )

        // Floating Top-Right Calories Badge (180 kcal)
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(14.dp)
                .shadow(2.dp, RoundedCornerShape(9999.dp))
                .clip(RoundedCornerShape(9999.dp))
                .background(SandSurfaceContainerLowest.copy(alpha = 0.92f))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_fire),
                contentDescription = null,
                tint = CaramelSecondary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = calories,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = EspressoPrimary
            )
        }

        // Floating Bottom Metadata Badges ("100% Arabica", "Medium Roast")
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 100% Arabica badge
            Row(
                modifier = Modifier
                    .shadow(2.dp, RoundedCornerShape(9999.dp))
                    .clip(RoundedCornerShape(9999.dp))
                    .background(SandSurfaceContainerLowest.copy(alpha = 0.92f))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_verified),
                    contentDescription = null,
                    tint = CaramelSecondary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = tag1,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = EspressoPrimary
                )
            }

            // Tag 2 badge
            Row(
                modifier = Modifier
                    .shadow(2.dp, RoundedCornerShape(9999.dp))
                    .clip(RoundedCornerShape(9999.dp))
                    .background(SandSurfaceContainerLowest.copy(alpha = 0.92f))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_local_cafe),
                    contentDescription = null,
                    tint = CaramelSecondary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = tag2,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = EspressoPrimary
                )
            }
        }
    }
}
