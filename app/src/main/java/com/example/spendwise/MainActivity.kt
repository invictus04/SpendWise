package com.example.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.ui.theme.SpendWiseTheme
import com.example.spendwise.Screens.SignUp
import com.example.spendwise.Screens.gettingStarted
import com.example.spendwise.Screens.Home
import com.example.spendwise.Screens.login
import com.example.spendwise.Screens.optverification

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendWiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = screen.gettingStarted.route) {
                            composable(screen.gettingStarted.route) {
                                gettingStarted(navController = navController)
                                
                            }
                        composable(screen.signUp.route) {
                            SignUp(navController = navController)
                        }
                        composable(screen.login.route) {
                                    login(navController = navController)
                        }
                        composable(screen.otpVerification.route) {
                                    optverification(navController = navController)
                        }
                        composable(screen.home.route){
                            Home(navController = navController)
                        }


                    }
                }
            }
        }
    }
}

