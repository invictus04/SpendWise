package com.example.spendwise

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.Screen.GettingStarted
import com.example.spendwise.Screen.HomeScreen
import com.example.spendwise.Screen.SplashScreen
import com.example.spendwise.Screen.addTransation
import com.example.spendwise.Screen.allExpensesScreen

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="/splash") {
        composable("/splash"){
            SplashScreen(navController)
        }
        composable("/start"){
            GettingStarted(navController)
        }
        composable("/home"){
            HomeScreen(navController)
        }
        composable("/all"){
            allExpensesScreen()
        }
        composable("/add"){
            addTransation(navController)
        }
    }
}