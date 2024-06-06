package com.example.spendwise.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
fun login(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("password") }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = "Sign Up page",
            modifier = Modifier.size(280.dp)
        )

        Text(
            text = "login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            letterSpacing = 1.5.sp,
            color = colorResource(id = R.color.grey)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = { Text(text = "Email")},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 15.dp)
                .size(60.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password")},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 20.dp, top = 10.dp)
                .size(60.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )

        Button(
            onClick = { navController.navigate(screen.home.route) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.85f)
        ) {
            Text(
                text = "Log In",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    letterSpacing = 1.1.sp
                )
            )
        }

        Row( modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an Account?",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp
                )
            )

            Text(
                text = "Send again",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp,
                    color = colorResource(id = R.color.red)
                ),
                modifier = Modifier
                    .clickable { navController.navigate(screen.signUp.route) }
                    .padding(start = 8.dp)
            )


        }

    }
}

@Preview(showBackground = true)
@Composable
private fun loginPreview() {
    login(rememberNavController())
}