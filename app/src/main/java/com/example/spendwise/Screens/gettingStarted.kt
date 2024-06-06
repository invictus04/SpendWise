package com.example.spendwise.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.R
import com.example.spendwise.screen
import com.example.spendwise.ui.theme.Inter

@Composable
fun gettingStarted(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.gettingstarted),
            contentDescription = "Getting Started Screen",
            alignment = Alignment.TopCenter,
            modifier = Modifier.padding(18.dp).size(350.dp)
        )


        Text(
            text = "SpendWise", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 27.sp,
            letterSpacing = 2.5.sp
        )
        Text(
            text = "Discover a new way of Expense Management", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.Light,
            fontSize = 19.sp,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.size(120.dp))
        Button(
            onClick = { navController.navigate(screen.signUp.route) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Register",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    letterSpacing = 1.1.sp
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedButton(
            onClick = { /*navigationToLogin()*/ navController.navigate(screen.login.route) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Sign In",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    letterSpacing = 1.1.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun gettingStartedPreview() {
    gettingStarted(rememberNavController())
}