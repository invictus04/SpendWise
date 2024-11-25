package com.example.spendwise.Screen

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.ui.theme.Inter
import com.example.spendwise.ui.theme.dark
import com.example.spendwise.ui.theme.light
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(5f).getInterpolation(it)
                }))
        delay(1500L)
        navController.navigate("/home")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors = listOf(
                    dark,
                    light
                )
            )
        ), contentAlignment = Alignment.Center) {
            Text(
                text = "SpendWise",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.White,
                letterSpacing = 4.sp,
                modifier = Modifier.scale(scale.value)
                
            )
    }


}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}