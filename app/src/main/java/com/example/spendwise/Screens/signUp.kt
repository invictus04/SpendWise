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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.R
import com.example.spendwise.screen
import com.example.spendwise.ui.theme.Inter

@Composable
fun SignUp(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = "Sign Up page",
            modifier = Modifier.size(280.dp)
        )
        Spacer(modifier = Modifier.padding(top = 38.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text(text = "username")},
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
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "email Address")},
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
            value = contact,
            onValueChange = {contact = it},
            label = { Text(text = "phone number")},
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
            label = { Text(text = "password")},
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
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            label = { Text(text = "Confirm Password")},
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

        Button(
            onClick = { navController.navigate(screen.otpVerification.route)},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.85f)
        ) {
            Text(
                text = "Sign Up",
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
                text = "Already have an Account?",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp
                )
            )

            Text(
                text = "login",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp,
                    color = colorResource(id = R.color.red)
                ),
                modifier = Modifier
                    .clickable { navController.navigate(screen.login.route) }
                    .padding(start = 8.dp)
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SignUp(rememberNavController())
}
