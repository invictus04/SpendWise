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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendwise.R
import com.example.spendwise.ui.theme.Inter

@Composable
fun SignUp() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.calculator),
            contentDescription = "Sign Up page",
            modifier = Modifier.size(280.dp)
        )
//            Icon(imageVector = ImageVector.vectorResource(id = R.drawable), contentDescription = )
        Spacer(modifier = Modifier.padding(top = 48.dp))

        OutlinedTextField(
            value = "username",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 15.dp)
                .size(50.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )

        OutlinedTextField(
            value = "email address",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 15.dp)
                .size(50.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )

        OutlinedTextField(
            value = "phone number",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 15.dp)
                .size(50.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )

        OutlinedTextField(
            value = "password",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 15.dp)
                .size(50.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )

        OutlinedTextField(
            value = "confirm password",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(bottom = 15.dp)
                .size(50.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )

        Button(
            onClick = { /*TODO*/ },
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


        Row(
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
                text = "Send again",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp
                ),
                modifier = Modifier.clickable { }.padding(start = 8.dp)
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    SignUp()
}
