package com.example.spendwise

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.spendwise.Screen.GettingStarted
import com.example.spendwise.Screen.HomeScreen
import com.example.spendwise.Screen.ProfileScreen
import com.example.spendwise.Screen.SplashScreen
import com.example.spendwise.Screen.StatsScreen
import com.example.spendwise.Screen.addTransation
import com.example.spendwise.Screen.allExpensesScreen
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.ui.theme.light
import com.example.spendwise.viewModel.HomeViewModel
import com.example.spendwise.viewModel.HomeViewModelFactory
import com.example.spendwise.viewModel.StatsViewModel
import com.example.spendwise.viewModel.StatsViewModelFactory


sealed class NavScreen(val route: String) {
    object func : NavScreen("fun")
    object Add : NavScreen("add")
    object All : NavScreen("all")

}


sealed class OnBoardingRoutes(val route: String) {
    object Board : OnBoardingRoutes("board")
    object GettingStarted : OnBoardingRoutes("start")
    object Splash : OnBoardingRoutes("splash")
}

sealed class BottomNavigation(val route: String, val icon: Int) {
    object Home : BottomNavigation("home", R.drawable.ic_home)
    object Stats : BottomNavigation("stats", R.drawable.ic_analysis)
    object Profile : BottomNavigation("profile", R.drawable.ic_user)
}


@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigation.Home,
        BottomNavigation.Stats,
        BottomNavigation.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val shouldShowBottomBar = currentRoute !in listOf(
        OnBoardingRoutes.Splash.route,
        OnBoardingRoutes.GettingStarted.route
    )


    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomBar(navController, bottomNavigationItems)
            }
        },
        floatingActionButton = {
            if (currentRoute == BottomNavigation.Home.route) {
                Image(painter = painterResource(id = R.drawable.ic_add), contentDescription = null, modifier = Modifier.clickable {
                    navController.navigate(NavScreen.Add.route)
                })
            }

        },


    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = OnBoardingRoutes.Board.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            navigation(
                startDestination = OnBoardingRoutes.Splash.route,
                route = OnBoardingRoutes.Board.route
            ) {
                composable(OnBoardingRoutes.Splash.route) {
                    SplashScreen(navController)
                }
                composable(OnBoardingRoutes.GettingStarted.route) {
                    GettingStarted(navController)
                }
            }

            navigation(
                startDestination = BottomNavigation.Home.route,
                route = "bottom"
            ) {
                composable(BottomNavigation.Home.route) {
                    val viewModel: HomeViewModel =
                        HomeViewModelFactory(LocalContext.current).create(HomeViewModel::class.java)
                    HomeScreen(navController, viewModel)
                }
                composable(BottomNavigation.Stats.route) {
                    val viewModel: StatsViewModel =
                        StatsViewModelFactory(LocalContext.current).create(StatsViewModel::class.java)
                    StatsScreen(navController, bottomNavigationItems, viewModel)
                }
                composable(BottomNavigation.Profile.route) {
                    ProfileScreen(navController)
                }
            }

            navigation(startDestination = NavScreen.Add.route, route = NavScreen.func.route) {
                composable(NavScreen.Add.route) {
                    addTransation(navController)
                }
                composable(NavScreen.All.route) {
                    allExpensesScreen()
                }
            }


        }
    }

}


@Composable
fun BottomBar(navController: NavController, items: List<BottomNavigation>) {
    NavigationBar(
        containerColor = Color.Transparent,
        tonalElevation = 5.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = null)
                },
                colors = NavigationBarItemColors(
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    selectedIconColor = light,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent
                )

            )
        }
    }

}


@Composable
fun TopAppBar(name: String, onBackClick: () -> Unit, icon: Painter) {
    androidx.compose.material.TopAppBar(title = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = name,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontFamily = Inter
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(painter = icon, contentDescription = null)
            }
        })
}

