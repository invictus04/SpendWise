package com.example.spendwise.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendwise.R
import com.example.spendwise.ui.theme.Inter

@Composable
fun optVerification() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.enterotp), contentDescription ="opt Verification",
            modifier = Modifier.size(350.dp) )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Enter OTP", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            letterSpacing = 1.5.sp
        )
        OutlinedTextField(value = "OTP",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .size(50.dp),
            shape = RoundedCornerShape(percent = 50),
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                letterSpacing = 1.1.sp
            )
        )
        OutlinedButton(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.85f).padding(15.dp)) {
            Text(text = "Verify",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    letterSpacing = 1.1.sp
                )
            )
        }

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
             ) {
            Text(text = "Didn't receive code?",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp
                )
            )
            Text(text = "Send again",
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    letterSpacing = 0.9.sp
                ),
                modifier = Modifier.clickable {  }.padding(start = 8.dp)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun optVerificationPreview() {
    optVerification()
}