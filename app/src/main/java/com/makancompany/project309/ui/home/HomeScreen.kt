package com.makancompany.project309.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makancompany.project309.R
import com.makancompany.project309.ui.home.components.BrewCraftBottomBar
import com.makancompany.project309.ui.home.components.BrewCraftClubCard
import com.makancompany.project309.ui.home.components.BrewCraftTopAppBar
import com.makancompany.project309.ui.home.components.CategoryChipRow
import com.makancompany.project309.ui.home.components.GreetingSection
import com.makancompany.project309.ui.home.components.PopularDrinksSection
import com.makancompany.project309.ui.home.components.SearchBarSection
import com.makancompany.project309.ui.home.components.SeasonalHeroBanner

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = remember { HomeViewModel() },
    onNavigateToCustomizer: (String) -> Unit = {},
    onNavigateToCheckout: () -> Unit = {},
    onNavigateToTracker: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.userMessage) {
        state.userMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            viewModel.onAction(HomeAction.DismissMessage)
        }
    }

    HomeScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateToCustomizer = onNavigateToCustomizer,
        onNavigateToCheckout = onNavigateToCheckout,
        onNavigateToTracker = onNavigateToTracker,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAction: (HomeAction) -> Unit,
    onNavigateToCustomizer: (String) -> Unit = {},
    onNavigateToCheckout: () -> Unit = {},
    onNavigateToTracker: () -> Unit = {},
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .shadow(6.dp, androidx.compose.foundation.shape.RoundedCornerShape(9999.dp))
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(9999.dp))
                            .background(com.makancompany.project309.ui.theme.SandOnSurface)
                            .padding(horizontal = 18.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = com.makancompany.project309.ui.theme.CaramelSecondaryFixed,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = data.visuals.message,
                            style = MaterialTheme.typography.labelMedium,
                            color = com.makancompany.project309.ui.theme.SandSurfaceContainerLowest
                        )
                    }
                }
            }
        },
        bottomBar = {
            BrewCraftBottomBar(
                selectedTab = state.selectedTab,
                cartBadgeCount = state.cartBadgeCount,
                onTabSelected = { tab ->
                    onAction(HomeAction.SelectTab(tab))
                    when (tab) {
                        BottomNavTab.CART -> onNavigateToCheckout()
                        BottomNavTab.ACTIVITY -> onNavigateToTracker()
                        else -> Unit
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 1. Top App Bar
            item(key = "top_app_bar") {
                BrewCraftTopAppBar(
                    branchName = state.branchName,
                    onBranchClick = { onAction(HomeAction.BranchSelectorClicked) },
                    onNotificationClick = { onAction(HomeAction.NotificationClicked) },
                    onProfileClick = { onAction(HomeAction.ViewPerksClicked) }
                )
            }

            // 2. Greeting & Status
            item(key = "greeting_section") {
                GreetingSection(
                    greeting = state.greeting,
                    userTier = state.userTier,
                    readyEstimate = state.readyEstimate
                )
            }

            // 3. Search Bar
            item(key = "search_bar") {
                SearchBarSection(
                    query = state.searchQuery,
                    onQueryChange = { onAction(HomeAction.UpdateSearchQuery(it)) },
                    onVoiceClick = { onAction(HomeAction.VoiceSearchClicked) },
                    onFilterClick = { onAction(HomeAction.FilterSearchClicked) }
                )
            }

            // 4. BrewCraft Club Card
            item(key = "club_card") {
                BrewCraftClubCard(
                    currentBeans = state.loyaltyCurrent,
                    maxBeans = state.loyaltyMax,
                    nextReward = state.loyaltyNextReward,
                    onViewPerksClick = { onAction(HomeAction.ViewPerksClicked) }
                )
            }

            // 5. Explore Categories
            item(key = "category_row") {
                CategoryChipRow(
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = { onAction(HomeAction.SelectCategory(it)) }
                )
            }

            // 6. Seasonal Promotional Hero
            item(key = "seasonal_hero") {
                SeasonalHeroBanner(
                    item = state.promoItem,
                    onTryNowClick = {
                        onNavigateToCustomizer("autumn_maple_latte")
                    }
                )
            }

            // 7. Popular Drinks Two-Column Grid
            item(key = "popular_drinks") {
                // Filter popular drinks if a category is selected (other than "All") and by search query
                val displayedDrinks = state.popularDrinks.filter { drink ->
                    val matchesCategory = state.selectedCategory == "All" ||
                            drink.category.contains(state.selectedCategory, ignoreCase = true) ||
                            drink.name.contains(state.selectedCategory, ignoreCase = true)

                    val matchesQuery = state.searchQuery.isBlank() ||
                            drink.name.contains(state.searchQuery, ignoreCase = true) ||
                            drink.subtitle.contains(state.searchQuery, ignoreCase = true)

                    matchesCategory && matchesQuery
                }.ifEmpty {
                    if (state.searchQuery.isNotBlank()) emptyList() else state.popularDrinks
                }

                PopularDrinksSection(
                    drinks = displayedDrinks,
                    favorites = state.favorites,
                    onFavoriteToggle = { onAction(HomeAction.ToggleFavorite(it)) },
                    onAddToCart = { onAction(HomeAction.AddToCart(it)) },
                    onDrinkClick = { drinkId ->
                        onNavigateToCustomizer(drinkId)
                    },
                    onSeeAllClick = { onAction(HomeAction.SelectTab(BottomNavTab.MENU)) }
                )
            }

            // Bottom Spacing
            item(key = "bottom_spacer") {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    com.makancompany.project309.ui.theme.Project309Theme {
        HomeScreen(
            state = HomeUiState(),
            onAction = {}
        )
    }
}

