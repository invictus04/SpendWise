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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
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
fun gettingStarted() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .verticalScroll(rememberScrollState())) {
    Image(painter = painterResource(id = R.drawable.gs),
        contentDescription ="Getting Started Screen",
        alignment = Alignment.Center,
        modifier = Modifier.padding(top =50.dp))

        Text(text = "SpendWise", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 27.sp,
            letterSpacing = 2.5.sp
        )
        Text(text = "Discover a new way of Expense Management", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.Light,
            fontSize = 19.sp,
            letterSpacing = 1.5.sp)

        Spacer(modifier = Modifier.size(150.dp))
       Button(onClick = { /*TODO*/ },
           shape = RoundedCornerShape(10.dp),
           modifier = Modifier.fillMaxWidth()
       ) {
           Text(text = "Sign Up",
               style = TextStyle(
                   fontFamily = Inter,
                   fontWeight = FontWeight.SemiBold,
                   fontSize = 15.sp,
                   letterSpacing = 1.1.sp
               )
           )
       }
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedButton(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register",
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
    gettingStarted()
}