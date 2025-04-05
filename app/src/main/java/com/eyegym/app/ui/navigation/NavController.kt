package com.eyegym.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eyegym.app.R
import com.eyegym.app.ui.screen.warmup_screen.WarmUpScreenRoot
import com.eyegym.app.ui.uikit.UiBottomNavigation

@Composable
fun NavController(
    startDestination: NavigationRoute = NavigationRoute.WarmUp,
) {
    val navController = rememberNavController()
    val configuration = LocalConfiguration.current
    val routes = listOf(
        TopLevelRoute(
            stringResource(R.string.warmup),
            NavigationRoute.WarmUp,
            ImageVector.vectorResource(R.drawable.outline_visibility_24)
        ),
        TopLevelRoute(
            stringResource(R.string.trips),
            NavigationRoute.Trips,
            ImageVector.vectorResource(R.drawable.baseline_menu_24)
        ),
        TopLevelRoute(
            stringResource(R.string.record),
            NavigationRoute.Record,
            ImageVector.vectorResource(R.drawable.baseline_event_note_24)
        )
    )
    NavHost(navController = navController, startDestination = startDestination) {

        composable<NavigationRoute.WarmUp> {
            WarmUpScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        navController = navController,
                        routes = routes)
                }
            )
        }
        /*composable<NavigationRoute.Registration> {
            RegistrationScreenRoot(
                onNavigateToDriverProfile = {
                    navController.navigate(NavigationRoute.DriverProfile)
                },
                onNavigateToClientProfile = {
                    navController.navigate(NavigationRoute.ClientProfile)
                }
            )
        }
        composable<NavigationRoute.DriverProfile> {
            AdminProfileScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        routes = adminRoutes,
                        navController = navController
                    )
                }
            )
        }

        composable<NavigationRoute.DriverOrders> {
            DriverOrdersScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        routes = adminRoutes,
                        navController = navController
                    )
                },
                onNavigateToOrderScreen = {
                    navController.navigate(NavigationRoute.DriverOrder(it))
                }
            )
        }

        composable<NavigationRoute.DriverCabinet> {
            DriverCabinetScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        routes = adminRoutes,
                        navController = navController
                    )
                }
            )
        }

        composable<NavigationRoute.ClientProfile> {
            ClientProfileScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        routes = clientRoutes,
                        navController = navController
                    )
                }
            )
        }

        composable<NavigationRoute.ClientExcursion> {
            ClientExcursionScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        routes = clientRoutes,
                        navController = navController
                    )
                },
                onNavigateToExcursionScreen = {
                    navController.navigate(NavigationRoute.Excursion(it))
                }
            )
        }

        composable<NavigationRoute.DriverOrder> {
            DriverOrderScreenRoot(
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        composable<NavigationRoute.Excursion> {
            ExcursionScreenRoot(
                onBackPressed = {
                    navController.navigateUp()
                },
                onNavigateToClientOrder = { orderId ->
                    navController.navigate(NavigationRoute.ClientOrder(orderId))
                }
            )
        }

        composable<NavigationRoute.ClientOrder> {
            ClientOrderScreenRoot(
                onBackPressed = {
                    navController.navigateUp()
                },
            )
        }

        composable<NavigationRoute.ClientCabinet> {
            ClientCabinetScreenRoot(
                bottomRoutes = {
                    UiBottomNavigation(
                        routes = clientRoutes,
                        navController = navController
                    )
                },
                onNavigateToClientOrder = { orderId ->
                    navController.navigate(NavigationRoute.ClientOrder(orderId))
                }
            )
        }*/

    }
}