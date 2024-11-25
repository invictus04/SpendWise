package com.example.spendwise

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.Screen.HomeScreen
import com.example.spendwise.Screen.SplashScreen
import com.example.spendwise.Screen.addTransation

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="/splash") {
        composable("/splash"){
            SplashScreen(navController)
        }
        composable("/home"){
            HomeScreen(navController)
        }
        composable("/add"){
            addTransation(navController)
        }
    }
}