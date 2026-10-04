package com.makancompany.project309.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makancompany.project309.R
import com.makancompany.project309.ui.home.BottomNavTab
import com.makancompany.project309.ui.theme.CaramelOnSecondary
import com.makancompany.project309.ui.theme.CaramelSecondary
import com.makancompany.project309.ui.theme.CaramelSecondaryContainer
import com.makancompany.project309.ui.theme.EspressoPrimary
import com.makancompany.project309.ui.theme.SandOnSurfaceVariant
import com.makancompany.project309.ui.theme.SandSurfaceContainer

@Composable
fun BrewCraftBottomBar(
    selectedTab: BottomNavTab,
    cartBadgeCount: Int,
    onTabSelected: (BottomNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = SandSurfaceContainer,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(72.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavTab.values().forEach { tab ->
                val isSelected = tab == selectedTab

                val iconRes = when (tab) {
                    BottomNavTab.HOME -> R.drawable.ic_nav_home
                    BottomNavTab.MENU -> R.drawable.ic_nav_menu
                    BottomNavTab.CART -> R.drawable.ic_nav_cart
                    BottomNavTab.ACTIVITY -> R.drawable.ic_nav_activity
                }

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Icon pill container (active indicator capsule for selected tab)
                    Box(
                        modifier = Modifier
                            .size(width = 64.dp, height = 32.dp)
                            .then(
                                if (isSelected) Modifier
                                    .clip(RoundedCornerShape(9999.dp))
                                    .background(CaramelSecondaryContainer)
                                else Modifier
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        // Icon with possible badge (Cart)
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(iconRes),
                                contentDescription = tab.title,
                                tint = if (isSelected) EspressoPrimary else SandOnSurfaceVariant,
                                modifier = Modifier.size(24.dp)
                            )

                            // Cart count badge
                            if (tab == BottomNavTab.CART && cartBadgeCount > 0) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .offset(x = 8.dp, y = (-4).dp)
                                        .size(16.dp)
                                        .clip(CircleShape)
                                        .background(CaramelSecondary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$cartBadgeCount",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 10.sp,
                                            lineHeight = 10.sp
                                        ),
                                        fontWeight = FontWeight.Bold,
                                        color = CaramelOnSecondary
                                    )
                                }
                            }
                        }
                    }

                    // Tab label
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) EspressoPrimary else SandOnSurfaceVariant
                    )
                }
            }
        }
    }
}
