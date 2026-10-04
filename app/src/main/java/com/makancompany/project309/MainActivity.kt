package com.makancompany.project309

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.makancompany.project309.ui.checkout.CheckoutAction
import com.makancompany.project309.ui.checkout.CheckoutRoute
import com.makancompany.project309.ui.checkout.CheckoutViewModel
import com.makancompany.project309.ui.customizer.CustomizerRoute
import com.makancompany.project309.ui.home.HomeRoute
import com.makancompany.project309.ui.theme.Project309Theme
import com.makancompany.project309.ui.theme.SandSurface
import com.makancompany.project309.ui.tracker.TrackerRoute

enum class AppDestination {
    Home,
    Customizer,
    Checkout,
    Tracker
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project309Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SandSurface
                ) {
                    var currentDestination by rememberSaveable {
                        mutableStateOf(AppDestination.Home)
                    }
                    var selectedDrinkId by rememberSaveable {
                        mutableStateOf("caramel_macchiato")
                    }
                    val checkoutViewModel = remember { CheckoutViewModel() }

                    BackHandler(enabled = currentDestination != AppDestination.Home) {
                        currentDestination = when (currentDestination) {
                            AppDestination.Tracker -> AppDestination.Checkout
                            AppDestination.Checkout -> AppDestination.Customizer
                            AppDestination.Customizer -> AppDestination.Home
                            AppDestination.Home -> AppDestination.Home
                        }
                    }

                    when (currentDestination) {
                        AppDestination.Home -> {
                            HomeRoute(
                                onNavigateToCustomizer = { drinkId ->
                                    selectedDrinkId = drinkId
                                    currentDestination = AppDestination.Customizer
                                },
                                onNavigateToCheckout = {
                                    currentDestination = AppDestination.Checkout
                                },
                                onNavigateToTracker = {
                                    currentDestination = AppDestination.Tracker
                                }
                            )
                        }
                        AppDestination.Customizer -> {
                            CustomizerRoute(
                                drinkId = selectedDrinkId,
                                onNavigateBack = {
                                    currentDestination = AppDestination.Home
                                },
                                onNavigateToCheckout = { cartItem ->
                                    checkoutViewModel.onAction(CheckoutAction.AddCustomizedItem(cartItem))
                                    currentDestination = AppDestination.Checkout
                                }
                            )
                        }
                        AppDestination.Checkout -> {
                            CheckoutRoute(
                                viewModel = checkoutViewModel,
                                onNavigateBack = {
                                    currentDestination = AppDestination.Customizer
                                },
                                onNavigateToTracker = {
                                    currentDestination = AppDestination.Tracker
                                }
                            )
                        }
                        AppDestination.Tracker -> {
                            TrackerRoute(
                                onNavigateBack = {
                                    currentDestination = AppDestination.Checkout
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}